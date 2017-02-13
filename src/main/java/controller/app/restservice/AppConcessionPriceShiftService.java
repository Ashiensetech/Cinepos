package controller.app.restservice;


import controller.app.AppUriPreFix;
import dao.ConcessionPriceShiftDao;
import entity.Combo;
import entity.ConcessionPriceShift;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;


@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/concession-price-shift")
public class AppConcessionPriceShiftService {
    @Autowired
    ConcessionPriceShiftDao concessionPriceShiftDao;

    @RequestMapping(value = "/get/{product_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductCombo(@PathVariable Integer product_id){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTimeInMillis());
        Date sDate=today;
        Date eDate=today;

        ConcessionPriceShift concessionPriceShift= concessionPriceShiftDao.getByProductAndDates(product_id,sDate,eDate);

        if(concessionPriceShift==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(concessionPriceShift);
        }

        return ResponseEntity.status(HttpStatus.OK).body(concessionPriceShift);

    }
}
