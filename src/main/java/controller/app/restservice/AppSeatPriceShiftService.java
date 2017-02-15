package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.SeatPriceShiftDao;
import dao.SeatTypeDao;
import entity.SeatPriceShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;

import java.util.Calendar;
import java.sql.Date;

/**
 * Created by Sarwar on 2/14/2017.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/seat-price-shift")
public class AppSeatPriceShiftService {
    @Autowired
    SeatPriceShiftDao seatPriceShiftDao;

    @Autowired
    SeatTypeDao seatTypeDao;


    @RequestMapping(value = "/get/{seat_type_id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProductCombo(@PathVariable Integer seat_type_id){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        System.out.println("dsdsdd");

        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTimeInMillis());
        Date sDate=today;
        Date eDate=today;

        SeatPriceShift seatPriceShift= seatPriceShiftDao.getBySeatAndDates(seat_type_id,sDate,eDate);

        if(seatPriceShift==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(seatPriceShift);
        }

        return ResponseEntity.status(HttpStatus.OK).body(seatPriceShift);

    }

}
