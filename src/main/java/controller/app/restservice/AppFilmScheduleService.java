package controller.app.restservice;


import com.fasterxml.jackson.annotation.JsonView;
import controller.app.AppUriPreFix;
import dao.FilmDao;
import dao.FilmScheduleDao;
import dao.FilmTimeDao;
import dao.ScreenDao;
import dao.viewDao.BoxOfficeSchedulingViewDao;
import entity.FilmSchedule;
import entity.Screen;
import entity.app.jsonview.film.schedule.FilmScheduleJsonView;
import entity.app.jsonview.view.BoxOfficeSchedulingViewJsonView;
import entity.app.jsonview.view.BoxOfficeScreenViewJsonView;
import entity.compositive.BoxOfficeScreen;
import entity.tableview.BoxOfficeSchedulingView;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utility.ServiceResponse;
import validator.admin.restservice.film.schedule.createOrMerge.CreateOrMergeValidator;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

    @JsonView(FilmScheduleJsonView.Summary.class)
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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmSchedules);
    }
    @JsonView(FilmScheduleJsonView.Details.class)
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
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmSchedules);
    }
    @JsonView(FilmScheduleJsonView.Details.class)
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


    @JsonView(BoxOfficeScreenViewJsonView.Summary.class)
    @RequestMapping(value = "/get-for-box-office-all-screen", method = RequestMethod.POST)
    public ResponseEntity<?> getForBoxOfficeOfAllScreen(@RequestParam(value = "date")String date) {
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
        List<Screen> screens = screenDao.getAllActivated();
        List<BoxOfficeScreen> boxOfficeScreens = new ArrayList<>();
        for(Screen screen : screens){
            BoxOfficeScreen boxOfficeScreen = new BoxOfficeScreen();

            boxOfficeScreen.setScreen(new Screen());
            boxOfficeScreen.getScreen().setId(screen.getId());
            boxOfficeScreen.getScreen().setName(screen.getName());
            boxOfficeScreen.getScreen().setNoOfSeat(screen.getNoOfSeat());

            FilmSchedule filmSchedule = filmScheduleDao.getByDate(screen.getId(), sDate);

            if(filmSchedule==null){
                boxOfficeScreens.add(boxOfficeScreen);
               continue;
            }

            List<BoxOfficeSchedulingView> boxOfficeSchedulingView = boxOfficeSchedulingViewDao.getByScheduleId(filmSchedule.getId());
            boxOfficeScreen.setBoxOffice(boxOfficeSchedulingView);
            boxOfficeScreens.add(boxOfficeScreen);
        }


        return ResponseEntity.status(HttpStatus.OK).body(boxOfficeScreens);
    }

    @JsonView(BoxOfficeScreenViewJsonView.Summary.class)
    @RequestMapping(value = "/get-for-box-office-all-screen/film-time-wise", method = RequestMethod.POST)
    public ResponseEntity<?> getScreenBoxOffice(@RequestParam(value = "date")String date) {
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


        List<Screen> screens = screenDao.getAllActivated();
        List<BoxOfficeScreen> boxOfficeScreens = new ArrayList<>();
        System.out.println("SOL");
        for(Screen screen : screens) {
            BoxOfficeScreen boxOfficeScreen = new BoxOfficeScreen();
            boxOfficeScreen.setScreen(new Screen());
            boxOfficeScreen.getScreen().setId(screen.getId());
            boxOfficeScreen.getScreen().setName(screen.getName());
            boxOfficeScreen.getScreen().setNoOfSeat(screen.getNoOfSeat());

            FilmSchedule filmSchedule = filmScheduleDao.getByDate(screen.getId(), sDate);

            if(filmSchedule==null){
                boxOfficeScreens.add(boxOfficeScreen);
                continue;
            }

            boxOfficeScreen.setFilmSchedule(filmSchedule);
            boxOfficeScreens.add(boxOfficeScreen);
        }


        return ResponseEntity.status(HttpStatus.OK).body(boxOfficeScreens);
    }


}
