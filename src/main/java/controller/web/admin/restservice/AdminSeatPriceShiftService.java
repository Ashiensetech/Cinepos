package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionPriceShiftDao;
import dao.ConcessionProductDao;
import dao.SeatPriceShiftDao;
import dao.SeatTypeDao;
import entity.ConcessionPriceShift;
import entity.SeatPriceShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminConcessionPriceShiftService.createConcessionPriceShift.CreateConcessionPriceShiftForm;
import validator.admin.AdminConcessionPriceShiftService.createConcessionPriceShift.CreateConcessionPriceShiftValidator;
import validator.admin.AdminSeatPriceShiftService.createSeatPriceShift.CreateSeatPriceShiftForm;
import validator.admin.AdminSeatPriceShiftService.createSeatPriceShift.CreateSeatPriceShiftValidator;

import javax.validation.Valid;

/**
 * Created by sunno on 1/16/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/seat-price-shift")
public class AdminSeatPriceShiftService {
    @Autowired
    SeatPriceShiftDao seatPriceShiftDao;

    @Autowired
    CreateSeatPriceShiftValidator createSeatPriceShiftValidator;

    @Autowired
    SeatTypeDao seatTypeDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createSeatPriceShift(@Valid CreateSeatPriceShiftForm createSeatPriceShiftForm, BindingResult result){

        System.out.println(createSeatPriceShiftForm);

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
        createSeatPriceShiftValidator.validate(createSeatPriceShiftForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        SeatPriceShift seatPriceShift = new SeatPriceShift();

        seatPriceShift.setSeatType(seatTypeDao.getById(createSeatPriceShiftForm.getSeatTypeId()));
        seatPriceShift.setStartDate(createSeatPriceShiftForm.getFormattedStartDate());
        seatPriceShift.setEndDate(createSeatPriceShiftForm.getFormattedEndDate());
        seatPriceShift.setPrice(createSeatPriceShiftForm.getPrice());
        seatPriceShift.setStatus(true);
        seatPriceShift.setCreatedBy(1);
        /***************** Service  [Ends] *************/

        System.out.println(seatPriceShift);

        seatPriceShift = seatPriceShiftDao.insert(seatPriceShift);

        return ResponseEntity.status(HttpStatus.OK).body(seatPriceShift);

    }


    @RequestMapping(value = "/delete/{priceShiftId}",method = RequestMethod.DELETE)
    public boolean deleteSeatPriceShift(@PathVariable Integer priceShiftId){
        return  seatPriceShiftDao.delete(seatPriceShiftDao.getById(priceShiftId));
    }

}
