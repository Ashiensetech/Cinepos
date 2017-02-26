package validator.admin.restservice.film.editFilm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.*;
import entity.Distributor;
import entity.Genre;
import entity.ScreenDimension;
import entity.TempFile;
import helper.DateHelper;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * Created by sunno on 1/11/17.
 */
@Service
public class EditFilmValidator implements Validator {

    @Autowired
    FilmDao filmDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    DistributorDao distributorDao;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    TempFileDao tempFileDao;

    @Override
    public void validate(Object obj, Errors errors) {
        EditFilmForm editFilmForm = (EditFilmForm) obj;
        TypeReference<List<Integer>> tRef = new TypeReference<List<Integer>>() {};
        TypeReference<Set<Integer>> tRefSet = new TypeReference<Set<Integer>>() {};
        if(editFilmForm.getName()!=null){
            if(editFilmForm.getName().length() > 50){
                errors.rejectValue("name", "Name is too large");
            }
        }

        if(editFilmForm.getGenreIds()!=null){
            /**
             * Film Genre
             * */
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                editFilmForm.setFilmGenreIdList(objectMapper.readValue(editFilmForm.getGenreIds(), tRef));
                List<Integer> filmGenreIdList = editFilmForm.getFilmGenreIdList();
                for (Integer filmGenreId : filmGenreIdList){
                    Genre filmGenre = genreDao.getById(filmGenreId);
                    if(filmGenre==null){
                        errors.rejectValue("genreIds", "Genre not found by id :"+filmGenreId);
                        break;
                    }
                }
            } catch (IOException e) {
                errors.rejectValue("genreIds","Broken String found");
            }
        }

        /**
         * Film Distributor
         * */
        if(editFilmForm.getDistributorId()!=null){
            Distributor distributor = distributorDao.getById(editFilmForm.getDistributorId());
            if (distributor==null) {
                errors.rejectValue("distributorId", "No distributor found");
            }

        }

        /**
         * Film Start date
         * */
        if(editFilmForm.getStartDate()!=null){
            try {
                editFilmForm.setFormattedStartDate(DateHelper.getStringToDate(editFilmForm.getStartDate(), "MM/dd/yyyy"));
            } catch (ParseException e) {
                errors.rejectValue("startDate", "Start Date format miss matched");
            }
        }

        /**
         * Film Finish date
         * */
        if(editFilmForm.getEndDate()!=null){
            try {
                editFilmForm.setFormattedEndDate(DateHelper.getStringToDate(editFilmForm.getEndDate(), "MM/dd/yyyy"));
            } catch (ParseException e) {
                errors.rejectValue("endDate", "End Date format miss matched");
            }
        }

        /**
         * Film rating,hour,duration(hour,min),trailer
         * */

        if(editFilmForm.getRating()!=null) {
            if (editFilmForm.getRating() < 0) {
                errors.rejectValue("rating", "Must be greater equal to zero");
            }
        }
        if(editFilmForm.getDurationHour()!=null){
            if (editFilmForm.getDurationHour() < 0) {
                errors.rejectValue("durationHour", "Must be greater equal to zero");
            }

        }
        if(editFilmForm.getDurationMin()!=null){
            if (editFilmForm.getDurationMin() < 0) {
                errors.rejectValue("durationMin", "Must be greater equal to zero");
            }
        }

        if(editFilmForm.getTrailer()!=null){
            String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
            UrlValidator urlValidator = new UrlValidator(schemes);
            if (!urlValidator.isValid(editFilmForm.getTrailer())) {
                errors.rejectValue("trailer", "Trailer url not in valid format");
            }
        }

        /**
         * Film Screen dimension
         * */
        if(editFilmForm.getScreenDimensions()!=null){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                editFilmForm.setScreenDimensionIdList(objectMapper.readValue(editFilmForm.getScreenDimensions(), tRefSet));
                Set<Integer> screenDimensionIdList = editFilmForm.getScreenDimensionIdList();
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
        }

        /**
         * Film Images
         * */
                 /*~~~~~~ Banner Image ~~~~~~~~~*/
        if(editFilmForm.getBannerImageToken()!=null ){
            if(editFilmForm.getBannerImageToken()<=0){
                errors.rejectValue("bannerImageToken", "Banner Image required");
            }else{
                TempFile tempFile = tempFileDao.getByToken(editFilmForm.getBannerImageToken());
                if(tempFile==null){
                    errors.rejectValue("bannerImageToken", "Invalid token found");
                }
            }
        }


            /*~~~~~~ Others Image ~~~~~~~~~*/
        if(editFilmForm.getOtherImagesToken()!=null && !editFilmForm.getOtherImagesToken().equals("")){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                editFilmForm.setOtherImagesTokenArray(objectMapper.readValue(editFilmForm.getOtherImagesToken(), tRef));

                List<TempFile> tempFileList = tempFileDao.getByToken(editFilmForm.getOtherImagesTokenArray());
                if(tempFileList==null){
                    errors.rejectValue("otherImagesToken", "Some of the token are invalid");
                }else{
                    if(tempFileList.size() != editFilmForm.getOtherImagesTokenArray().size() ){
                        errors.rejectValue("otherImagesToken", "Some of the token are invalid");
                    }
                }
            } catch (IOException e) {
                errors.rejectValue("otherImagesToken", "Broken json string found");
            }
        }
              /*~~~~~~ Deleted Images ~~~~~~~~~*/

        if(editFilmForm.getDeletedImagesIds()!=null && !editFilmForm.getDeletedImagesIds().equals("")){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                editFilmForm.setDeletedImagesIdSet(objectMapper.readValue(editFilmForm.getDeletedImagesIds(), tRefSet));
            } catch (IOException e) {
                errors.rejectValue("deletedImagesIds", "Broken json string found");
            }
        }
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return EditFilmForm.class.equals(aClass);
    }

}
