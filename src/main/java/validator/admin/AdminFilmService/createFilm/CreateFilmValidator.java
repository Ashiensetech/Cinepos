package validator.admin.AdminFilmService.createFilm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.*;
import entity.*;
import helper.DateHelper;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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

        boolean excpFlag = false;
        try {
            createFilmForm.setFormattedStartDate(DateHelper.getStringToDate(createFilmForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
            excpFlag=true;
        }

        try {
            createFilmForm.setFormattedEndDate(DateHelper.getStringToDate(createFilmForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
            excpFlag=true;
        }

        if(!excpFlag){
            if(createFilmForm.getFormattedStartDate().after(createFilmForm.getFormattedEndDate())){
                errors.rejectValue("endDate", "End Date is past then start date");
            }
        }

        /**
         *Film Trailer
        * */
        String[] schemes = {"http","https"}; // DEFAULT schemes = "http", "https", "ftp"
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(createFilmForm.getTrailer())) {
            errors.rejectValue("trailer", "Trailer url not in valid format");
        }

        /**
         * Film Images
        * */

        if(createFilmForm.getBannerImageToken()<=0){
            errors.rejectValue("bannerImageToken", "Banner Image required");
        }else{
            TempFile tempFile = tempFileDao.getByToken(createFilmForm.getBannerImageToken());
            if(tempFile==null){
                errors.rejectValue("bannerImageToken", "Invalid token found");
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Integer>> tRef = new TypeReference<List<Integer>>() {};

        try {
            createFilmForm.setOtherImagesTokenArray(objectMapper.readValue(createFilmForm.getOtherImagesToken(), tRef));
            if(createFilmForm.getOtherImagesToken()!=null && !createFilmForm.getOtherImagesToken().equals("")) {
                List<TempFile> tempFileList = tempFileDao.getByToken(createFilmForm.getOtherImagesTokenArray());
                if (tempFileList == null) {
                    errors.rejectValue("otherImagesToken", "Some of the token are invalid");
                } else {
                    if (tempFileList.size() != createFilmForm.getOtherImagesTokenArray().size()) {
                        errors.rejectValue("otherImagesToken", "Some of the token are invalid");
                    }
                }
            }
        } catch (IOException e) {
            errors.rejectValue("otherImagesToken", "Broken json");
        }



        /**
         * Film Screen dimension
         * */

        try {
            ObjectMapper screenDimensionObjectMapper = new ObjectMapper();
            createFilmForm.setScreenDimensionIdList(screenDimensionObjectMapper.readValue(createFilmForm.getScreenDimensions(), tRef));
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
            ObjectMapper filmGenreObjectMapper = new ObjectMapper();
            createFilmForm.setFilmGenreIdList(filmGenreObjectMapper.readValue(createFilmForm.getGenreIds(), tRef));
            List<Integer> filmGenreIdList = createFilmForm.getFilmGenreIdList();
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
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateFilmForm.class.equals(aClass);
    }

}
