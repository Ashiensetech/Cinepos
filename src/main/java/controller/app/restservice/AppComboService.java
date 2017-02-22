package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.ComboDao;
import entity.Combo;
import entity.ConcessionProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;

import java.util.List;

/**
 * Created by Sarwar on 1/31/2017.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/combo")
public class AppComboService {
    @Autowired
    ComboDao comboDao;

    @RequestMapping(value = "/get/all/{type}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductCombo(@PathVariable String type){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        List<Combo> comboList= comboDao.getAllProductCombo(type);

        if(comboList==null || comboList.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(comboList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(comboList);
    }

    @RequestMapping(value = "/get/{combo_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductCombo(@PathVariable Integer combo_id){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        Combo combos= comboDao.getById(combo_id);

        if(combos==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(combos);
        }

        return ResponseEntity.status(HttpStatus.OK).body(combos);
    }




}
