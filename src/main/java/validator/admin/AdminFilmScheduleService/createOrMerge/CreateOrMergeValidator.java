package validator.admin.AdminFilmScheduleService.createOrMerge;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.*;
import entity.Film;
import entity.FilmSchedule;
import entity.FilmTime;
import entity.Screen;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 1/24/17.
 */
@Service
public class CreateOrMergeValidator implements Validator {
    @Autowired
    FilmDao filmDao;

    @Autowired
    ScreenDao screenDao;

    @Autowired
    FilmScheduleDao filmScheduleDao;

    @Autowired
    FilmTimeDao filmTimeDao;
    @Override
    public void validate(Object obj, Errors errors) {
        CreateOrMergeForm createOrMergeForm = (CreateOrMergeForm) obj;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            createOrMergeForm.scheduleForm = objectMapper.readValue(createOrMergeForm.getScheduleJson(), ScheduleForm.class);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            errors.rejectValue("scheduleJson", "Broken Json String");
            return;
        }
        if(createOrMergeForm.scheduleForm==null){
            errors.rejectValue("scheduleJson", "Broken Json return null");
            return;
        }
        List<FilmTimeForm> filmTimeForms = createOrMergeForm.scheduleForm.getFilmTime();

        Screen screen = screenDao.getById(createOrMergeForm.getScreenId());


        if(screen==null){
            errors.rejectValue("screenId", "Screen not found by id :"+createOrMergeForm.getScreenId());
        }



        FilmSchedule filmSchedule = null;
        if(createOrMergeForm.scheduleForm.getId()==0){
            filmSchedule = filmScheduleDao.getByDate(createOrMergeForm.getScreenId(), createOrMergeForm.scheduleForm.getDate());
        }


        if(filmSchedule!=null){
            errors.rejectValue("scheduleJson", "Film Schedule already exist on date "+createOrMergeForm.scheduleForm.getDate());
        }
        for(FilmTimeForm filmTimeForm:filmTimeForms){
            Film film = filmDao.getById(filmTimeForm.getFilmId());
            if(film==null){
                errors.rejectValue("film", "Film not found");
                break;
            }

            if(filmTimeForm.getStartTime().after(filmTimeForm.getEndTime())){
                errors.rejectValue("scheduleJson", "Start date is greater then end time");
                break;
            }

        }



        System.out.println(createOrMergeForm.scheduleForm);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateOrMergeForm.class.equals(aClass);
    }
}
