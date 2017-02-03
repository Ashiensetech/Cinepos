package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.*;
import entity.*;
import entity.jspView.TicketSeat;
import helper.ScreenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mi on 2/3/17.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/screen-seat")
public class AppScreenSeatService {


    @Autowired
    ScreenSeatDao screenSeatDao;

    @Autowired
    ScreenDao screenDao;

    @RequestMapping(value = "/get-by-screen-id/{screenId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTicketByFilmTimeId(@PathVariable Integer screenId){
        Screen screen = screenDao.getById(screenId);
        /**
         * Checking Screen existence
         * */
         screenSeatDao.getByScreenId(screenId);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Error return
         * */
        if(screen == null ){
            serviceResponse.setValidationError("screenId","No screen found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /**
         * Get screen seats
         * */
        List< ScreenSeat> screenSeatList = screen.getSeats();

        /**
         * Error return
         * */
        if(screenSeatList==null || screenSeatList.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ServiceResponse.getMsg("Screen have not created any seats"));
        }

        /**
         * Return Screen Seat JSON
         * */
        List< List<ScreenSeat>> screenSeats =  ScreenHelper.singleDimensionToTwoDimensionList(screenSeatList, screen.getRowCount(), screen.getColumnCount());
        if(screenSeats==null || screenSeats.size()==0){
            ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(screenSeats);
    }
}
