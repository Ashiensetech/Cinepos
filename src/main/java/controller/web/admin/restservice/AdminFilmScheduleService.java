package controller.web.admin.restservice;


import controller.web.admin.AdminUriPreFix;
import dao.FilmDao;
import dao.FilmScheduleDao;
import dao.FilmTimeDao;
import dao.ScreenDao;
import entity.AuthCredential;
import entity.Film;
import entity.FilmSchedule;
import entity.FilmTime;
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
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeForm;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeValidator;
import validator.admin.AdminFilmScheduleService.createOrMerge.FilmTimeForm;
import validator.admin.AdminFilmScheduleService.editFlimTime.EditFilmTimeValidator;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
/**
 * Created by mi on 1/2/17.
 */

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/film-scheduling")
public class AdminFilmScheduleService {
    @Autowired
    CreateOrMergeValidator createOrMergeValidator;

    @Autowired
    EditFilmTimeValidator editFilmTimeValidator;

    @Autowired
    FilmDao filmDao;

    @Autowired
    FilmScheduleDao filmScheduleDao;

    @Autowired
    FilmTimeDao filmTimeDao;

    @Autowired
    ScreenDao screenDao;

    @RequestMapping(value = "/create-merge", method = RequestMethod.POST)
    public ResponseEntity<?> createOrMerge(Authentication authentication,
                                           @Valid CreateOrMergeForm createOrMergeForm,
                                           BindingResult result) {

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
        createOrMergeValidator.validate(createOrMergeForm,result);
        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
    /***************** Validation  [End] *************/



    /***************** Service [Started] *************/


        /**
         * FilmSchedule object data entry
         * */
        FilmSchedule filmSchedule = new FilmSchedule();

        if(createOrMergeForm.scheduleForm.getId()>0){
            filmSchedule = filmScheduleDao.getById(createOrMergeForm.scheduleForm.getId());
            if(filmSchedule == null){
                serviceResponse.setValidationError("filmSchedule","Film schedule not found");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }
        }else{
            filmSchedule.setScreen(screenDao.getById(createOrMergeForm.getScreenId()));
            filmSchedule.setDate(createOrMergeForm.scheduleForm.getDate());
            filmSchedule.setCreatedBy(loggedInUser.getId());
            filmSchedule.setStatus(true);
        }

        Set<FilmTime> filmTimes = new HashSet<>();
        List<FilmTimeForm> filmTimeForms = createOrMergeForm.scheduleForm.getFilmTime();


    /***************** Service   [End] *************/

    /***************** DATA BASE Persistence [Start] *******************/
        filmScheduleDao.insertOrUpdate(filmSchedule);
    /***************** DATA BASE Persistence [End] *******************/


        /**
         * Preparing film time with film schedule id
        * */
        for(FilmTimeForm filmTimeForm:filmTimeForms){
            FilmTime filmTime = new FilmTime();
            if(filmTimeForm.getId()>0){
                filmTime = filmTimeDao.getById(filmTimeForm.getId());
                if(filmTime == null){
                    serviceResponse.setValidationError("filmTime","Film time not found");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
                }
            }else{
                filmTime.setStatus(true);
                filmTime.setFilmScheduleId(filmSchedule.getId());
                filmTime.setStartTime(filmTimeForm.getStartTime());
                filmTime.setEndTime(filmTimeForm.getEndTime());
                filmTime.setFilm(filmDao.getById(filmTimeForm.getFilmId()));
                filmTime.setCreatedBy(loggedInUser.getId());
            }


            filmTimes.add(filmTime);
        }

    /***************** DATA BASE Persistence [Start] *******************/
        filmTimeDao.insertOrUpdate(filmTimes);
    /***************** DATA BASE Persistence [End] *******************/

        filmSchedule.setFilmTimes(filmTimes);


        return ResponseEntity.status(HttpStatus.OK).body(filmSchedule);
    }

    @RequestMapping(value = "/update/film-time/{filmTimeId}", method = RequestMethod.POST)
    public ResponseEntity<?> editFilmTime(Authentication authentication,
                                          @PathVariable Integer filmTimeId,
                                           @Valid FilmTimeForm filmTimeForm,
                                           BindingResult result) {
        AuthCredential loggedInUser = (AuthCredential)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        filmTimeForm.setId(filmTimeId);
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
        editFilmTimeValidator.validate(filmTimeForm,result);
        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        FilmTime filmTime = filmTimeDao.getById(filmTimeForm.getId());

        List<FilmTime> collusionFilmTime = filmTimeDao.getByInBetweenTime(filmTime.getId(),filmTime.getFilmScheduleId(), filmTimeForm.getStartTime(), filmTimeForm.getEndTime());

        if(collusionFilmTime!=null && collusionFilmTime.size()>0){
            System.out.println(collusionFilmTime);
            serviceResponse.setValidationError("sTime","Film time collied");
            serviceResponse.setValidationError("eTime","Film time collied");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service [Started] *************/


        Film film = filmDao.getById(filmTimeForm.getFilmId());
        filmTime.setFilm(film);
        filmTime.setFilmScheduleId(filmTimeForm.getScheduleId());
        filmTime.setStartTime(filmTimeForm.getStartTime());
        filmTime.setEndTime(filmTimeForm.getEndTime());
        /***************** Service [Ends] *************/


        /***************** DATA BASE Persistence [Start] *******************/
        filmTimeDao.update(filmTime);
        /***************** DATA BASE Persistence [End] *******************/


        return ResponseEntity.status(HttpStatus.OK).body(filmTime);
    }

    @RequestMapping(value = "/delete/film-time/{filmTimeId}", method = RequestMethod.POST)
    public ResponseEntity<?> deleteFilmTime(Authentication authentication,
                                          @PathVariable Integer filmTimeId) {
        AuthCredential loggedInUser = (AuthCredential)authentication.getPrincipal();
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        /***************** Validation  [Start] *************/

        /***************** Validation  [End] *************/

        /**
         * Business logic validation
         * */

        FilmTime filmTime =filmTimeDao.getById(filmTimeId);

        /**
         * [ Delete ] Constrain check is must be later when ticket is done
         * Good luck!!
        * */






        if(filmTime!=null){
            /***************** DATA BASE Persistence [Start] *******************/
            filmTimeDao.delete(filmTime);
            /***************** DATA BASE Persistence [End] *******************/
        }




        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
