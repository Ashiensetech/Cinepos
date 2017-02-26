package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.FilmDao;
import dao.FilmRentalDao;
import entity.FilmRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.restservice.film.rental.createFilmRental.CreateFilmRentalForm;
import validator.admin.restservice.film.rental.createFilmRental.CreateFilmRentalValidator;
import validator.admin.restservice.film.rental.editFilmRental.EditFilmRentalForm;
import validator.admin.restservice.film.rental.editFilmRental.EditFilmRentalValidator;

import javax.validation.Valid;

/**
 * Created by sunno on 1/23/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/film-rental")
public class AdminFilmRentalService {
    @Autowired
    FilmRentalDao filmRentalDao;

    @Autowired
    CreateFilmRentalValidator createFilmRentalValidator;

    @Autowired
    EditFilmRentalValidator editFilmRentalValidator;

    @Autowired
    FilmDao filmDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createFilmRental(@Valid CreateFilmRentalForm createFilmRentalForm, BindingResult result){

        System.out.println(createFilmRentalForm);

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /***************** Validation  [Start] *************/

        /**
         * Basic form validation
         * */
        serviceResponse.bindValidationError(result);
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Business logic validation
         * */
        createFilmRentalValidator.validate(createFilmRentalForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        FilmRental filmRental= new FilmRental();

        filmRental.setFilm(filmDao.getById(createFilmRentalForm.getFilmId()));
        filmRental.setWeekName(createFilmRentalForm.getWeekName());
        filmRental.setStartDate(createFilmRentalForm.getFormattedStartDate());
        filmRental.setEndDate(createFilmRentalForm.getFormattedEndDate());
        filmRental.setCreatedBy(1);
        /***************** Service  [Ends] *************/

        System.out.println(filmRental);

        filmRental = filmRentalDao.insert(filmRental);

        return ResponseEntity.status(HttpStatus.OK).body(filmRental);

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> editFilmRental(@Valid EditFilmRentalForm editFilmRentalForm, BindingResult result){

        System.out.println(editFilmRentalForm);

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /***************** Validation  [Start] *************/

        /**
         * Basic form validation
         * */
        serviceResponse.bindValidationError(result);
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Business logic validation
         * */
        editFilmRentalValidator.validate(editFilmRentalForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        FilmRental filmRental= filmRentalDao.getById(editFilmRentalForm.getId());
        filmRental.setFilm(filmDao.getById(editFilmRentalForm.getFilmId()));
        filmRental.setStartDate(editFilmRentalForm.getFormattedStartDate());
        filmRental.setEndDate(editFilmRentalForm.getFormattedEndDate());
        filmRental.setWeekName(editFilmRentalForm.getWeekName());
//        seatPriceShift.setCreatedBy(1);
        /***************** Service  [Ends] *************/

        System.out.println(filmRental);
        filmRentalDao.update(filmRental);

        return ResponseEntity.status(HttpStatus.OK).body(filmRental);
    }

    @RequestMapping(value = "/delete/{filmRentalId}",method = RequestMethod.DELETE)
    public boolean deleteFilmRental(@PathVariable Integer filmRentalId){
        return  filmRentalDao.delete(filmRentalDao.getById(filmRentalId));
    }

}
