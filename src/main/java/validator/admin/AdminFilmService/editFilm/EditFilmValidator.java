package validator.admin.AdminFilmService.editFilm;

import dao.DistributorDao;
import dao.FilmDao;
import entity.Distributor;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.text.ParseException;

/**
 * Created by sunno on 1/11/17.
 */
@Service
public class EditFilmValidator implements Validator {

    @Autowired
    FilmDao filmDao;

    @Autowired
    DistributorDao distributorDao;

    @Override
    public void validate(Object obj, Errors errors) {
        EditFilmForm editFilmForm = (EditFilmForm) obj;

        Distributor distributor = distributorDao.getById(editFilmForm.getDistributorId());
        System.out.print(distributor);
        if (distributor==null){
            errors.rejectValue("distributorId", "No distributor found");
        }

        try {
            editFilmForm.setFormattedStartDate(DateHelper.getStringToDate(editFilmForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            editFilmForm.setFormattedEndDate(DateHelper.getStringToDate(editFilmForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }


    }
    @Override
    public boolean supports(Class<?> aClass) {
        return EditFilmForm.class.equals(aClass);
    }

}
