package controller.app.restservice;


import controller.app.AppUriPreFix;
import controller.web.admin.AdminUriPreFix;
import dao.FilmDao;
import dao.FilmScheduleDao;
import dao.FilmTimeDao;
import dao.ScreenDao;
import dao.viewDao.BoxOfficeSchedulingViewDao;
import entity.AuthCredential;
import entity.FilmSchedule;
import entity.FilmTime;
import entity.entityView.BoxOfficeSchedulingView;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utility.ServiceResponse;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeForm;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeValidator;
import validator.admin.AdminFilmScheduleService.createOrMerge.FilmTimeForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mi on 1/2/17.
 */

@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/film-scheduling")
public class AppFilmScheduleService {
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
    @Autowired
    BoxOfficeSchedulingViewDao boxOfficeSchedulingViewDao;

    @RequestMapping(value = "/get-all-in-date-range/{screenId}", method = RequestMethod.POST)
    public ResponseEntity<?> getAll(@PathVariable(value = "screenId")Integer screenId,
                                    @RequestParam(value = "startDate") String startDate,
                                    @RequestParam(value = "endDate") String endDate) {
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        Date sDate = null;
        Date eDate = null;
        if(startDate!=null){
            try {
                sDate = DateHelper.getStringToDate(startDate,"yyyy-MM-dd");
            } catch (ParseException e) {
                serviceResponse.setValidationError("startDate", e.getMessage());
            }
        }else{
            serviceResponse.setValidationError("startDate", "Start date required");
        }
        if(endDate!=null){
            try {
                eDate = DateHelper.getStringToDate(endDate,"yyyy-MM-dd");
            } catch (ParseException e) {
                serviceResponse.setValidationError("endDate", e.getMessage());
            }
        }else{
            serviceResponse.setValidationError("endDate", "End date required");
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        List<FilmSchedule> filmSchedules = filmScheduleDao.getByDateRange(screenId,sDate,eDate);

        if(filmSchedules==null || filmSchedules.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(filmSchedules);
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmSchedules);
    }
    @RequestMapping(value = "/get-by-date/{screenId}", method = RequestMethod.POST)
    public ResponseEntity<?> getByDate(@PathVariable(value = "screenId")Integer screenId,
                                    @RequestParam(value = "startDate") String startDate) {
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        Date sDate = null;
        if(startDate!=null){
            try {
                sDate = DateHelper.getStringToDate(startDate,"yyyy-MM-dd");
            } catch (ParseException e) {
                serviceResponse.setValidationError("startDate", e.getMessage());
            }
        }else{
            serviceResponse.setValidationError("startDate", "Start date required");
        }
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        FilmSchedule filmSchedules = filmScheduleDao.getByDate(screenId, sDate);

        if(filmSchedules==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(filmSchedules);
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmSchedules);
    }
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> getById(@PathVariable(value = "id")Integer id) {
        if(id==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body( ServiceResponse.getMsg("Id required"));
        }


        FilmSchedule filmSchedules = filmScheduleDao.getById(id);

        if(filmSchedules==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmSchedules);
    }
    @RequestMapping(value = "/get-for-box-office", method = RequestMethod.POST)
    public ResponseEntity<?> getForBoxOfficeByDate(@RequestParam(value = "screen_id")Integer screenId,
                                                    @RequestParam(value = "date")String date) {
        Date sDate = null;
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        if(date!=null){
            try {
                sDate = DateHelper.getStringToDate(date,"yyyy-MM-dd");
            } catch (ParseException e) {
                serviceResponse.setValidationError("startDate", e.getMessage());
            }
        }else{
            serviceResponse.setValidationError("startDate", "Start date required");
        }

        if(serviceResponse.hasErrors()){
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        FilmSchedule filmSchedule = filmScheduleDao.getByDate(screenId,sDate);
        if(filmSchedule==null){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ServiceResponse.getMsg("No scheduling information found"));
        }

        List<BoxOfficeSchedulingView> boxOfficeSchedulingView = boxOfficeSchedulingViewDao.getByScheduleId(filmSchedule.getId());

        if(boxOfficeSchedulingView==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ServiceResponse.getMsg("No film time found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(boxOfficeSchedulingView);
    }
    @RequestMapping(value = "/get-for-box-office/{scheduleId}", method = RequestMethod.POST)
    public ResponseEntity<?> getForBoxOfficeByDate(@PathVariable(value = "scheduleId")Integer scheduleId) {
        ServiceResponse serviceResponse = ServiceResponse.getInstance();




        FilmSchedule filmSchedule = filmScheduleDao.getById(scheduleId);
        if(filmSchedule==null){
            serviceResponse.setValidationError("scheduleId","No scheduling information found");
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(serviceResponse.getFormError());
        }


        List<BoxOfficeSchedulingView> boxOfficeSchedulingView = boxOfficeSchedulingViewDao.getByScheduleId(filmSchedule.getId());

        if(boxOfficeSchedulingView==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ServiceResponse.getMsg("No film time found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(boxOfficeSchedulingView);
    }
}
