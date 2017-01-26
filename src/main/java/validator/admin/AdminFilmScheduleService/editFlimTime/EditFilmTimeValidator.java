package validator.admin.AdminFilmScheduleService.editFlimTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FilmDao;
import dao.FilmScheduleDao;
import dao.FilmTimeDao;
import dao.ScreenDao;
import entity.Film;
import entity.FilmSchedule;
import entity.FilmTime;
import entity.Screen;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeForm;
import validator.admin.AdminFilmScheduleService.createOrMerge.FilmTimeForm;
import validator.admin.AdminFilmScheduleService.createOrMerge.ScheduleForm;

import java.io.IOException;
import java.util.List;

/**
 * Created by mi on 1/24/17.
 */
@Service
public class EditFilmTimeValidator implements Validator {
    @Autowired
    FilmDao filmDao;

    @Autowired
    ScreenDao screenDao;

    @Autowired
    FilmTimeDao filmTimeDao;

    @Override
    public void validate(Object obj, Errors errors) {
        FilmTimeForm filmTimeForm = (FilmTimeForm) obj;

        FilmTime filmTime = filmTimeDao.getById(filmTimeForm.getId());
        if(filmTime==null){
            errors.rejectValue("id","No film time found with id :"+filmTimeForm.getId());
            return;
        }

        /*Check for conflict */
        // Code here

        /**
         * Film existence
         * */
        Film film = filmDao.getById(filmTimeForm.getFilmId());
        if(film==null) {
            errors.rejectValue("filmId", "Film not found");
        }
        try {
            filmTimeForm.setStartTime(DateHelper.getStringToTime(filmTimeForm.getsTime()));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            errors.rejectValue("sTime","Time format mismatched");
        }
        try {
            filmTimeForm.setEndTime(DateHelper.getStringToTime(filmTimeForm.geteTime()));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            errors.rejectValue("eTime","Time format mismatched");
        }








    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateOrMergeForm.class.equals(aClass);
    }
}
