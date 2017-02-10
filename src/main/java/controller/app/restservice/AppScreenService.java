package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.ScreenDao;
import entity.ConcessionProduct;
import entity.Screen;
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

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllScreen(){

        List<Screen> screens= screenDao.getAll();

        if(screens==null || screens.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(screens);
        }

        return ResponseEntity.status(HttpStatus.OK).body(screens);
    }

    @RequestMapping(value = "/get/{screen_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getScreenById(@PathVariable Integer screen_id){

        Screen screen= screenDao.getById(screen_id);

        if(screen==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(screen);
        }

        return ResponseEntity.status(HttpStatus.OK).body(screen);
    }

}
