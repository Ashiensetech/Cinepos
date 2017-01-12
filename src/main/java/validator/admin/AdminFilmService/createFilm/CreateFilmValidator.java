package validator.admin.AdminFilmService.createFilm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.*;
import entity.*;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by sunno on 1/11/17.
 */
@Service
public class CreateFilmValidator implements Validator {

    @Autowired
    FilmDao filmDao;

    @Autowired
    DistributorDao distributorDao;

    @Autowired
    TempFileDao tempFileDao;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    GenreDao genreDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateFilmForm createFilmForm = (CreateFilmForm) obj;

        Distributor distributor = distributorDao.getById(createFilmForm.getDistributorId());
        if (distributor==null){
            errors.rejectValue("distributorId", "No distributor found");
        }


        try {
            createFilmForm.setFormattedStartDate(DateHelper.getStringToDate(createFilmForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            createFilmForm.setFormattedEndDate(DateHelper.getStringToDate(createFilmForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }
        if(createFilmForm.getBannerImageToken()<=0){
            errors.rejectValue("bannerImageToken", "Banner Image required");
        }else{
            TempFile tempFile = tempFileDao.getByToken(createFilmForm.getBannerImageToken());
            if(tempFile==null){
                errors.rejectValue("bannerImageToken", "Invalid token found");
            }
        }

        /**
         * Film Images
        * */
        if(createFilmForm.getOtherImagesToken()!=null && !createFilmForm.getOtherImagesToken().equals("")){
            TypeReference<List<Integer>> tRef = new TypeReference<List<Integer>>() {};
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                createFilmForm.setOtherImagesTokenArray(objectMapper.readValue(createFilmForm.getOtherImagesToken(), tRef));

                List<TempFile> tempFileList = tempFileDao.getByToken(createFilmForm.getOtherImagesTokenArray());
                if(tempFileList==null){
                    errors.rejectValue("otherImagesToken", "Some of the token are invalid");
                }else{
                    if(tempFileList.size() != createFilmForm.getOtherImagesTokenArray().size() ){
                        errors.rejectValue("otherImagesToken", "Some of the token are invalid");
                    }
                }
            } catch (IOException e) {
                errors.rejectValue("bannerImageToken", "Broken json string found");
            }


            /**
             * Film Screen dimension
             * */

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                createFilmForm.setScreenDimensionIdList(objectMapper.readValue(createFilmForm.getScreenDimensions(), tRef));
                List<Integer> screenDimensionIdList = createFilmForm.getScreenDimensionIdList();
                for (Integer screenDimensionId : screenDimensionIdList){
                    ScreenDimension screenDimension = screenDimensionDao.getById(screenDimensionId);
                    if(screenDimension==null){
                        errors.rejectValue("screenDimensions", "Screen dimension not found by id :"+screenDimensionId);
                        break;
                    }
                }


            } catch (IOException e) {
                errors.rejectValue("screenDimensions","Broken String found");
            }



            /**
             * Film Genre
             * */
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                createFilmForm.setFilmGenreIdList(objectMapper.readValue(createFilmForm.getGenreIds(), tRef));
                List<Integer> filmGenreIdList = createFilmForm.getFilmGenreIdList();
                for (Integer filmGenreId : filmGenreIdList){
                    Genre filmGenre = genreDao.getById(filmGenreId);
                    if(filmGenre==null){
                        errors.rejectValue("filmGenre", "Genre not found by id :"+filmGenreId);
                        break;
                    }
                }


            } catch (IOException e) {
                errors.rejectValue("filmGenre","Broken String found");
            }


        }

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateFilmForm.class.equals(aClass);
    }

}
