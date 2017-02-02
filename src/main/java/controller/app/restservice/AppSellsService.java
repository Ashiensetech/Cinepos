package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.SellsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sarwar on 2/2/2017.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/sells")
public class AppSellsService {
    @Autowired
    SellsDao sellsDao;

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public ResponseEntity<?> create() {
//        String errorMsg="Order create successfully";
//
//
//    }
}
