package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionPriceShiftDao;
import dao.ConcessionProductDao;
import entity.AuthCredential;
import entity.ConcessionPriceShift;
import entity.ConcessionProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminConcessionPriceShiftService.createConcessionPriceShift.CreateConcessionPriceShiftForm;
import validator.admin.AdminConcessionPriceShiftService.createConcessionPriceShift.CreateConcessionPriceShiftValidator;
import validator.admin.AdminConcessionPriceShiftService.editConcessionPriceShift.EditConcessionPriceShiftForm;
import validator.admin.AdminConcessionPriceShiftService.editConcessionPriceShift.EditConcessionPriceShiftValidator;
import validator.admin.AdminSeatPriceShiftService.editSeatPriceShift.EditSeatPriceShiftValidator;

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

    @Autowired
    EditConcessionPriceShiftValidator editConcessionPriceShiftValidator;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createConcessionPriceShift(Authentication authentication,
                                                        @Valid CreateConcessionPriceShiftForm createConcessionPriceShiftForm,
                                                        BindingResult result){
        AuthCredential currentLoggedInUser = (AuthCredential)authentication.getPrincipal();
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


        ConcessionProduct concessionProduct =  concessionProductDao.getById(createConcessionPriceShiftForm.getConcessionProductId());

        /***************** Service  [Start] *************/
        ConcessionPriceShift concessionPriceShift= new ConcessionPriceShift();

        concessionPriceShift.setConcessionProduct(concessionProduct);
        concessionPriceShift.setStartDate(createConcessionPriceShiftForm.getFormattedStartDate());
        concessionPriceShift.setEndDate(createConcessionPriceShiftForm.getFormattedEndDate());
        concessionPriceShift.setPrice(createConcessionPriceShiftForm.getPrice());
        concessionPriceShift.setStatus(true);
        concessionPriceShift.setCreatedBy(currentLoggedInUser.getId());
        /***************** Service  [Ends] *************/


        boolean priceShiftInsertFlag = concessionPriceShiftDao.insert(concessionPriceShift);

        if(!priceShiftInsertFlag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ServiceResponse.getMsg("Internal server error while inserting Price shift"));
        }

        /**
         * Setting is price shift true
         * */
        concessionProduct.setIsPriceShift(1);
        boolean concessionProductUpdateFlag =concessionProductDao.update(concessionProduct);

        if(!concessionProductUpdateFlag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ServiceResponse.getMsg("Internal server error while updating concession product"));
        }


        return ResponseEntity.status(HttpStatus.OK).body(concessionPriceShift);

    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<?> updateConcessionPriceShift(@Valid EditConcessionPriceShiftForm editConcessionPriceShiftForm, BindingResult result){

        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        ConcessionPriceShift concessionPriceShift = concessionPriceShiftDao.getById(editConcessionPriceShiftForm.getId());

        if(concessionPriceShift==null){
            serviceResponse.setValidationError("id","No concession price shift found by ID :"+editConcessionPriceShiftForm.getId());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        if(!concessionPriceShift.getStatus()){
            serviceResponse.setValidationError("id","Can't update inactive concession price shift");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


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
        editConcessionPriceShiftValidator.validate(editConcessionPriceShiftForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/

        System.out.println(editConcessionPriceShiftForm);

        concessionPriceShift.setConcessionProduct(concessionProductDao.getById(editConcessionPriceShiftForm.getConcessionProductId()));
        concessionPriceShift.setStartDate(editConcessionPriceShiftForm.getFormattedStartDate());
        concessionPriceShift.setEndDate(editConcessionPriceShiftForm.getFormattedEndDate());
        concessionPriceShift.setPrice(editConcessionPriceShiftForm.getPrice());
        concessionPriceShift.setStatus(true);
        concessionPriceShift.setCreatedBy(1);
        /***************** Service  [Ends] *************/

        System.out.println(concessionPriceShift);

        concessionPriceShiftDao.update(concessionPriceShift);

        return ResponseEntity.status(HttpStatus.OK).body(concessionPriceShift);

    }

    @RequestMapping(value = "/delete/{priceShiftId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteConcessionPriceShift(@PathVariable Integer priceShiftId){
        ConcessionPriceShift concessionPriceShift = concessionPriceShiftDao.getById(priceShiftId);
        ConcessionProduct concessionProduct = concessionPriceShift.getConcessionProduct();


        if(concessionPriceShift.getStatus()){
            concessionProduct.setIsPriceShift(0);
        }

        boolean deleteFlag = concessionPriceShiftDao.delete(concessionPriceShiftDao.getById(priceShiftId));

        if(!deleteFlag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ServiceResponse.getMsg("Internal server error while deleting price shift"));
        }

        boolean updateFlag = concessionProductDao.update(concessionProduct);
        if(!updateFlag){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ServiceResponse.getMsg("Internal server error while updating concession product"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg("Deleted successfully"));
    }

}
