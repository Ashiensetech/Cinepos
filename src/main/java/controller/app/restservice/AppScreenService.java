package controller.app.restservice;

import com.fasterxml.jackson.annotation.JsonView;
import controller.app.AppUriPreFix;
import dao.ScreenDao;
import dao.ScreenSeatDao;
import entity.Screen;
import entity.ScreenSeat;
import entity.app.jsonview.screen.ScreenJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sarwar on 2/6/2017.
 */

@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/screen")
public class AppScreenService {
    @Autowired
    ScreenDao screenDao;

    @Autowired
    ScreenSeatDao screenSeatDao;

    @JsonView(ScreenJsonView.Summary.class)
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllScreen(){

        List<Screen> screens= screenDao.getSummaryAll();

        if(screens==null || screens.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(screens);
        }

        return ResponseEntity.status(HttpStatus.OK).body(screens);
    }

    @JsonView(ScreenJsonView.Basic.class)
    @RequestMapping(value = "/get/all/basic", method = RequestMethod.GET)
    public ResponseEntity<?> getAllShortScreen(){

        List<Screen> screens= screenDao.getShortAll();

        if(screens==null || screens.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(screens);
        }

        return ResponseEntity.status(HttpStatus.OK).body(screens);
    }
    @JsonView(ScreenJsonView.Details.class)
    @RequestMapping(value = "/get/{screen_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getScreenById(@PathVariable Integer screen_id){

        Screen screen= screenDao.getById(screen_id);

        if(screen==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(screen);
        }

        return ResponseEntity.status(HttpStatus.OK).body(screen);
    }
    @RequestMapping(value = "/get-screen-seat/{screen_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getScreenSeatById(@PathVariable Integer screen_id){

        List<ScreenSeat> screenSeats= screenSeatDao.getByScreenId(screen_id);

        if(screenSeats==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(screenSeats);
        }

        return ResponseEntity.status(HttpStatus.OK).body(screenSeats);
    }

}
