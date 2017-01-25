package validator.admin.AdminFilmScheduleService.createOrMerge;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FilmDao;
import dao.FilmRentalDao;
import dao.ScreenDao;
import entity.Film;
import entity.FilmTime;
import entity.Screen;
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
    FilmRentalDao filmRentalDao;

    @Autowired
    FilmDao filmDao;

    @Autowired
    ScreenDao screenDao;


    @Override
    public void validate(Object obj, Errors errors) {
        CreateOrMergeForm createOrMergeForm = (CreateOrMergeForm) obj;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            createOrMergeForm.scheduleForm = objectMapper.readValue(createOrMergeForm.getScheduleJson(), ScheduleForm.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            errors.rejectValue("scheduleJson", "Broken Json String");
        }
        List<FilmTimeForm> filmTimeForms = createOrMergeForm.scheduleForm.getFilmTime();

        Screen screen = screenDao.getById(createOrMergeForm.getScreenId());

        if(screen==null){
            errors.rejectValue("screenId", "Screen not found by id :"+createOrMergeForm.getScreenId());
        }

        for(FilmTimeForm filmTimeForm:filmTimeForms){
            Film film = filmDao.getById(filmTimeForm.getFilmId());
            if(film==null){
                errors.rejectValue("film", "Film not found");
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
