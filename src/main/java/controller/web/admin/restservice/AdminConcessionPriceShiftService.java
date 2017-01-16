package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionPriceShiftDao;
import dao.ConcessionProductDao;
import entity.ConcessionPriceShift;
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

import javax.validation.Valid;

/**
 * Created by sunno on 1/16/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/concession-price-shift")
public class AdminConcessionPriceShiftService {
    @Autowired
    ConcessionPriceShiftDao concessionPriceShiftDao;

    @Autowired
    CreateConcessionPriceShiftValidator createConcessionPriceShiftValidator;

    @Autowired
    ConcessionProductDao concessionProductDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createConcessionPriceShift(@Valid CreateConcessionPriceShiftForm createConcessionPriceShiftForm, BindingResult result){

        System.out.println(createConcessionPriceShiftForm);

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
        createConcessionPriceShiftValidator.validate(createConcessionPriceShiftForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        ConcessionPriceShift concessionPriceShift= new ConcessionPriceShift();


        System.out.println(createConcessionPriceShiftForm);

//        concessionPriceShift.setConcessionProduct(concessionProductDao.getById(createConcessionPriceShiftForm.getConcessionProductId()));
        concessionPriceShift.setStartDate(createConcessionPriceShiftForm.getFormattedStartDate());
        concessionPriceShift.setEndDate(createConcessionPriceShiftForm.getFormattedEndDate());
        concessionPriceShift.setPrice(createConcessionPriceShiftForm.getPrice());
        concessionPriceShift.setStatus(true);
        concessionPriceShift.setCreatedBy(1);
        /***************** Service  [Ends] *************/

        System.out.println(concessionPriceShift);

        concessionPriceShift = concessionPriceShiftDao.insert(concessionPriceShift);

        return ResponseEntity.status(HttpStatus.OK).body(concessionPriceShift);

    }


    @RequestMapping(value = "/delete/{priceShiftId}",method = RequestMethod.DELETE)
    public boolean deleteSeatType(@PathVariable Integer priceShiftId){
        return  concessionPriceShiftDao.delete(concessionPriceShiftDao.getById(priceShiftId));
    }

}
