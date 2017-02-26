package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.GenreDao;
import dao.SeatTypeDao;
import entity.AuthCredential;
import entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.restservice.genre.createGenre.CreateGenreForm;
import validator.admin.restservice.genre.createGenre.CreateGenreValidator;
import validator.admin.restservice.genre.editGenre.EditGenreForm;
import validator.admin.restservice.genre.editGenre.EditGenreValidator;

import javax.validation.Valid;

/**
 * Created by sunno on 1/6/17.
 */

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/genre")
public class AdminGenreService {
    @Autowired
    GenreDao genreDao;

    @Autowired
    SeatTypeDao seatTypeDao;

    @Autowired
    CreateGenreValidator createGenreValidator;

    @Autowired
    EditGenreValidator editGenreValidator;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createSeatType(Authentication authentication,
                                            @Valid CreateGenreForm createGenreForm,
                                            BindingResult result){

        AuthCredential loggedInUser = (AuthCredential)authentication.getPrincipal();
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
        createGenreValidator.validate(createGenreForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        Genre genre = new Genre();

        genre.setName(createGenreForm.getName());
        genre.setCreatedBy(loggedInUser.getId());
        /***************** Service  [Ends] *************/

        genreDao.insert(genre);


        return ResponseEntity.status(HttpStatus.OK).body(genre);

    }


    @RequestMapping(value = "/update/{genreId}",method = RequestMethod.POST)
    public ResponseEntity<?> updateSeatType(@Valid EditGenreForm editGenreForm, BindingResult result,
                                            @PathVariable Integer genreId){
        System.out.println(editGenreForm);

        Genre genre = genreDao.getById(genreId);

        editGenreForm.setId(genreId);

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        if(genre==null){
            serviceResponse.setValidationError("genreId","No genre found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


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
        editGenreValidator.validate(editGenreForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/


        genre.setName(editGenreForm.getName());
        /***************** Service  [Ends] *************/

        genreDao.update(genre);

        return ResponseEntity.status(HttpStatus.OK).body(genre);

    }


    @RequestMapping(value = "/delete/{genreId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGenre(@PathVariable Integer genreId){
        Genre genre = genreDao.getById(genreId);
        if(genre==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ServiceResponse.getMsg("No genre found"));
        }
        genreDao.delete(genre);
        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg("Deleted genre"));
    }
}
