package validator.admin.AdminFilmScheduleService.createOrMerge;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.FilmDao;
import dao.FilmRentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;

/**
 * Created by mi on 1/24/17.
 */
@Service
public class CreateOrMergeValidator implements Validator {
    @Autowired
    FilmRentalDao filmRentalDao;

    @Autowired
    FilmDao filmDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateOrMergeForm createOrMergeForm = (CreateOrMergeForm) obj;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            createOrMergeForm.scheduleForm = objectMapper.readValue(createOrMergeForm.getScheduleJson(), ScheduleForm.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(createOrMergeForm.scheduleForm);
        System.out.println(createOrMergeForm.scheduleForm.getFilmTime().get(0).getStartTime());
        System.out.println(createOrMergeForm.scheduleForm.getFilmTime().get(0).getEndTime());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateOrMergeForm.class.equals(aClass);
    }
}
