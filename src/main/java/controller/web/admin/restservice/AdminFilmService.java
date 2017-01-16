package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import custom_exception.TempFileException;
import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.FileUtil;
import utility.ServiceResponse;
import validator.admin.AdminFilmService.createFilm.CreateFilmForm;
import validator.admin.AdminFilmService.createFilm.CreateFilmValidator;
import validator.admin.AdminFilmService.editFilm.EditFilmForm;
import validator.admin.AdminFilmService.editFilm.EditFilmValidator;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by sunno on 1/11/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/film")
public class AdminFilmService {

    @Autowired
    FilmDao filmDao;

    @Autowired
    CreateFilmValidator createFilmValidator;

    @Autowired
    EditFilmValidator editFilmValidator;

    @Autowired
    DistributorDao distributorDao;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    FilmScreenTypeDao filmScreenTypeDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createFilm(@Valid CreateFilmForm createFilmForm, BindingResult result){

        System.out.println(createFilmForm);

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /***************** Validation  [Start] *************/

        /**
         * Basic form validation
         * */
        serviceResponse.bindValidationError(result);
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Business logic validation
         * */
        createFilmValidator.validate(createFilmForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        Film film = new Film();

        film.setName(createFilmForm.getName());

        film.setDistributor(distributorDao.getById(createFilmForm.getDistributorId()));

        film.setDurationHour(createFilmForm.getDurationHour());
        film.setDurationMin(createFilmForm.getDurationMin());
        film.setRating(createFilmForm.getRating());
        film.setStatus(createFilmForm.getStatus());
        film.setIsPriceShift(createFilmForm.getIsPriceShift());
        film.setStartDate(createFilmForm.getFormattedStartDate());
        film.setEndDate(createFilmForm.getFormattedEndDate());





        /**
         *  Film Screen dimension
         *  */
        Set<ScreenDimension> screenDimensions = new HashSet<>();
        List<Integer> screenDimensionIdList = createFilmForm.getScreenDimensionIdList();

        for (Integer screenDimensionId :screenDimensionIdList){
            ScreenDimension screenDimension = screenDimensionDao.getById(screenDimensionId);
            if(screenDimension!=null){
                screenDimensions.add(screenDimension);
            }
        }
        film.setScreenDimensions(screenDimensions);


        /**
         *  Film Genre
         *  */
        List<Genre> genres = new ArrayList<>();
        List<Integer> genreIds = createFilmForm.getFilmGenreIdList();
        for (Integer genreId :genreIds){
            Genre genre = genreDao.getById(genreId);
            if(genre!=null){
                genres.add(genre);
            }
        }
        film.setFilmGenre(genres);

        /***************** Service  [Ends] *************/

        /**
         * Insert Film
         * */
        filmDao.insert(film);


        /**
         *  Film Trailer
         *  */
        List<FilmTrailer> filmTrailerList = new ArrayList<>();
        FilmTrailer filmTrailer = new FilmTrailer();

        filmTrailer.setTrailerUrl(createFilmForm.getTrailer());
        filmTrailerList.add(filmTrailer);

        film.setFilmTrailers(filmTrailerList);

        /**
         * Film Banner Image
         * */
        List<FilmImage> filmImages = new ArrayList<>();

        FilmImage filmBannerImage = new FilmImage();
        filmBannerImage.setIsBanner(true);
        try {
            String filePath = fileUtil.moveFilmFileFromTemp(film.getId(),createFilmForm.getBannerImageToken());
            filmBannerImage.setFilePath(filePath);
            filmImages.add(filmBannerImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TempFileException e) {
            e.printStackTrace();
        }

        /**
         *  Film Other Images
         *  */
        List<Integer> otherImagesToken = createFilmForm.getOtherImagesTokenArray();

        for(Integer token : otherImagesToken){
            try {
                FilmImage filmOtherImage = new FilmImage();
                String filePath = fileUtil.moveFilmFileFromTemp(film.getId(),token);
                filmOtherImage.setFilePath(filePath);

                filmImages.add(filmOtherImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (TempFileException e) {
                e.printStackTrace();
            }
        }

        film.setFilmImages(filmImages);


        /**
         * Updating Film
        * */
        filmDao.update(film);



        return ResponseEntity.status(HttpStatus.OK).body(film);

    }
    @RequestMapping(value = "/edit/{filmId}",method = RequestMethod.POST)
    public ResponseEntity<?> editFilm(@PathVariable Integer filmId
                                    ,@Valid EditFilmForm editFilmForm,
                                      BindingResult result){


        Film film = filmDao.getById(filmId);

        /***************** Film Existence *************/
        ServiceResponse serviceResponse  = ServiceResponse.getInstance();
        if(film==null){
            serviceResponse.setValidationError("filmId","No film found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        /***************** Validation  [Start] *************/

        /**
         * Basic form validation
         * */
        serviceResponse.bindValidationError(result);
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Business logic validation
         * */
        editFilmValidator.validate(editFilmForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/


        /***************** Service  [Start] *************/
        if(editFilmForm.getName()!=null)film.setName(editFilmForm.getName());
        if(editFilmForm.getDistributorId()!=null)film.setDistributor(distributorDao.getById(editFilmForm.getDistributorId()));
        if(editFilmForm.getDurationHour()!=null)film.setDurationHour(editFilmForm.getDurationHour());
        if(editFilmForm.getDurationMin()!=null)film.setDurationMin(editFilmForm.getDurationMin());
        if(editFilmForm.getRating()!=null)film.setRating(editFilmForm.getRating());
        if(editFilmForm.getFormattedStartDate()!=null)film.setStartDate(editFilmForm.getFormattedStartDate());
        if(editFilmForm.getFormattedEndDate()!=null)film.setEndDate(editFilmForm.getFormattedEndDate());






        /**
         *  Film Screen dimension
         ***/
        Set<Integer> screenDimensionIdList = editFilmForm.getScreenDimensionIdList();
        if( screenDimensionIdList!=null && screenDimensionIdList.size()>0){
            Set<ScreenDimension> screenDimensions = new HashSet<>();
            for (Integer screenDimensionId :screenDimensionIdList){
                ScreenDimension screenDimension = screenDimensionDao.getById(screenDimensionId);
                screenDimensions.add(screenDimension);
            }
            film.setScreenDimensions(screenDimensions);
        }

        /**
         *  Film Genre
         *  */
        List<Genre> genres = new ArrayList<>();
        List<Integer> genreIds = editFilmForm.getFilmGenreIdList();
        if(genreIds!=null && genreIds.size()>0){
            for (Integer genreId :genreIds){
                Genre genre = genreDao.getById(genreId);
                if(genre!=null){
                    genres.add(genre);
                }
            }
            film.setFilmGenre(genres);
        }

        /**
         * Film Banner Image
         * */
        if(editFilmForm.getBannerImageToken()!=null){
            FilmImage filmBannerImage = new FilmImage();

            Optional<FilmImage> optionalBannerImage = film.getFilmImages().stream().filter(filmImage -> filmImage.getIsBanner()).findFirst();

            if(optionalBannerImage.isPresent()){
                filmBannerImage = optionalBannerImage.get();
            }

            filmBannerImage.setIsBanner(true);
            try {
                String filePath = fileUtil.moveFilmFileFromTemp(film.getId(),editFilmForm.getBannerImageToken());
                filmBannerImage.setFilePath(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (TempFileException e) {
                e.printStackTrace();
            }
        }

        /**
         *  Film Other Images
         *  */
        if(editFilmForm.getBannerImageToken()!=null){
            List<Integer> otherImagesToken = editFilmForm.getOtherImagesTokenArray();
            List<FilmImage> filmImages = film.getFilmImages();
            if(filmImages==null){
                filmImages = new ArrayList<>();
            }
            for(Integer token : otherImagesToken){
                try {
                    FilmImage filmOtherImage = new FilmImage();
                    String filePath = fileUtil.moveFilmFileFromTemp(film.getId(),token);
                    filmOtherImage.setFilePath(filePath);

                    filmImages.add(filmOtherImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (TempFileException e) {
                    e.printStackTrace();
                }
            }
            film.setFilmImages(filmImages);
        }

        /***************** Service  [Ends] *************/


        filmDao.update(film);

        return ResponseEntity.status(HttpStatus.OK).body(film);

    }

}
