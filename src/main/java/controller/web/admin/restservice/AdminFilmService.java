package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import custom_exception.TempFileException;
import dao.DistributorDao;
import dao.FilmDao;
import dao.GenreDao;
import dao.ScreenDimensionDao;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.FileUtil;
import utility.ServiceResponse;
import validator.admin.AdminFilmService.createFilm.CreateFilmForm;
import validator.admin.AdminFilmService.createFilm.CreateFilmValidator;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    DistributorDao distributorDao;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    GenreDao genreDao;

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
        List<ScreenDimension> screenDimensions = new ArrayList<>();
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


}
