package validator.admin.AdminFilmRentalService.createFilmRental;

import dao.FilmDao;
import dao.FilmRentalDao;
import entity.Film;
import entity.FilmRental;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;

/**
 * Created by sunno on 1/23/17.
 */
@Service
public class CreateFilmRentalValidator implements Validator {
    @Autowired
    FilmRentalDao filmRentalDao;

    @Autowired
    FilmDao filmDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateFilmRentalForm filmRentalForm = (CreateFilmRentalForm) obj;

        try {
            filmRentalForm.setFormattedStartDate(DateHelper.getStringToDate(filmRentalForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            filmRentalForm.setFormattedEndDate(DateHelper.getStringToDate(filmRentalForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }

        if (filmRentalForm.getFormattedEndDate().before(filmRentalForm.getFormattedStartDate())) {
            errors.rejectValue("endDate", "End Date should be after start date");
        }
        FilmRental filmRental = filmRentalDao.getByFilmAndDates(filmRentalForm.getFilmId(),filmRentalForm.getFormattedStartDate(),filmRentalForm.getFormattedEndDate());
        if(filmRental!=null){
            errors.rejectValue("startDate", "Start date or end date already exist");
        }

        Film film= filmDao.getById(filmRentalForm.getFilmId());
        if(film==null){
            errors.rejectValue("filmId", "Film does not exists");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateFilmRentalForm.class.equals(aClass);
    }
}
