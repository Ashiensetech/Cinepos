package validator.admin.AdminFilmService.createFilm;

import dao.DistributorDao;
import dao.FilmDao;
import entity.Distributor;
import entity.Film;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.admin.AdminSeatType.createSeatType.CreateSeatTypeForm;

import java.text.ParseException;

/**
 * Created by sunno on 1/11/17.
 */
@Service
public class CreateFilmValidator implements Validator {

    @Autowired
    FilmDao filmDao;

    @Autowired
    DistributorDao distributorDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateFilmForm createFilmForm = (CreateFilmForm) obj;

        Distributor distributor = distributorDao.getById(createFilmForm.getDistributorId());
        System.out.print(distributor);
        if (distributor==null){
            errors.rejectValue("distributorId", "No distributor found");
        }

        try {
            createFilmForm.setFormattedStartDate(DateHelper.getStringToDate(createFilmForm.getStartDate(), "dd/MM/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            createFilmForm.setFormattedEndDate(DateHelper.getStringToDate(createFilmForm.getEndDate(), "dd/MM/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }


    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateSeatTypeForm.class.equals(aClass);
    }

}
