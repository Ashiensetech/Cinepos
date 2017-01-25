package controller.web.admin.restservice;


import controller.web.admin.AdminUriPreFix;
import dao.FilmDao;
import dao.FilmScheduleDao;
import dao.FilmTimeDao;
import dao.ScreenDao;
import entity.AuthCredential;
import entity.FilmSchedule;
import entity.FilmTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeForm;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeValidator;
import validator.admin.AdminFilmScheduleService.createOrMerge.FilmTimeForm;

import javax.servlet.http.HttpServletRequest;
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
                                           BindingResult result,HttpServletRequest request) {

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
        filmSchedule.setScreen(screenDao.getById(createOrMergeForm.getScreenId()));
        filmSchedule.setDate(createOrMergeForm.scheduleForm.getDate());
        filmSchedule.setCreatedBy(loggedInUser.getId());
        filmSchedule.setStatus(true);

        Set<FilmTime> filmTimes = new HashSet<>();
        List<FilmTimeForm> filmTimeForms = createOrMergeForm.scheduleForm.getFilmTime();


        /***************** Service   [End] *************/

        /***************** DATA BASE Persistence [Start] *******************/
        filmScheduleDao.insert(filmSchedule);


        for(FilmTimeForm filmTimeForm:filmTimeForms){
            FilmTime filmTime = new FilmTime();
            filmTime.setStatus(true);
            filmTime.setFilmScheduleId(filmSchedule.getId());
            filmTime.setStartTime(filmTimeForm.getStartTime());
            filmTime.setEndTime(filmTimeForm.getEndTime());
            filmTime.setFilm(filmDao.getById(filmTimeForm.getFilmId()));

            filmTimes.add(filmTime);
        }


        if(filmTimes.size()>0)filmTimeDao.insertOrUpdate(filmTimes);

        /***************** DATA BASE Persistence [End] *******************/

        filmSchedule.setFilmTimes(filmTimes);


        return ResponseEntity.status(HttpStatus.OK).body(filmSchedule);
    }

}
