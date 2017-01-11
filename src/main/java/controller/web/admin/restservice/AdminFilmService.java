package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import dao.FilmDao;
import entity.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminFilmService.createFilm.CreateFilmForm;
import validator.admin.AdminFilmService.createFilm.CreateFilmValidator;

import javax.validation.Valid;

/**
 * Created by sunno on 1/11/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/film")
public class AdminFilmService {

    @Autowired
    FilmDao filmDao;

    @Autowired
    CreateFilmValidator createFilmValidator;

    @Autowired
    DistributorDao distributorDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createFilm(@Valid CreateFilmForm createFilmForm, BindingResult result){

        System.out.println(createFilmForm);

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
        createFilmValidator.validate(createFilmForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        Film film = new Film();

        film.setName(createFilmForm.getName());

        film.setDistributor(distributorDao.getById(createFilmForm.getDistributorId()));

        film.setDuration(createFilmForm.getDuration());
        film.setRating(createFilmForm.getRating());
        film.setStatus(createFilmForm.getStatus());
        film.setIsPriceShift(createFilmForm.getIsPriceShift());
        film.setStartDate(createFilmForm.getFormattedStartDate());
        film.setEndDate(createFilmForm.getFormattedEndDate());
        film.setCreatedBy(1);
        /***************** Service  [Ends] *************/

//        film = filmDao.insert(film);
        filmDao.insert(film);


        return ResponseEntity.status(HttpStatus.OK).body(film);

    }


}
