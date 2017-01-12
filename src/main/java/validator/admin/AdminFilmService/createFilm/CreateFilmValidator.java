package validator.admin.AdminFilmService.createFilm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DistributorDao;
import dao.FilmDao;
import dao.TempFileDao;
import entity.Distributor;
import entity.Film;
import entity.TempFile;
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

        if(createFilmForm.getOtherImagesToken()!=null && !createFilmForm.getOtherImagesToken().equals("")){
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                TypeReference<List<Integer>> tRef = new TypeReference<List<Integer>>() {};
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
        }

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateFilmForm.class.equals(aClass);
    }

}
