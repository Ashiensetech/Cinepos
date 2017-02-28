package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.SeatTypeDao;
import entity.AuthCredential;
import entity.SeatType;
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
import validator.admin.restservice.seat.type.createSeatType.CreateSeatTypeForm;
import validator.admin.restservice.seat.type.createSeatType.CreateSeatTypeValidator;
import validator.admin.restservice.seat.type.editSeatType.EditSeatTypeForm;
import validator.admin.restservice.seat.type.editSeatType.EditSeatTypeValidator;

import javax.validation.Valid;

/**
 * Created by sunno on 1/6/17.
 */

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/seat-type")
public class AdminSeatTypeService {
    @Autowired
    SeatTypeDao seatTypeDao;

    @Autowired
    CreateSeatTypeValidator createSeatTypeValidator;

    @Autowired
    EditSeatTypeValidator editSeatTypeValidator;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createSeatType(@Valid CreateSeatTypeForm createSeatTypeForm, BindingResult result){

        System.out.println(createSeatTypeForm);

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
        createSeatTypeValidator.validate(createSeatTypeForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        SeatType seatType = new SeatType();

        seatType.setName(createSeatTypeForm.getName());
        seatType.setAdultPrice(createSeatTypeForm.getAdultPrice());
        seatType.setChildPrice(createSeatTypeForm.getChildPrice());
        seatType.setIsDefault(createSeatTypeForm.getIsDefault());
        seatType.setCreatedBy(1);
        /***************** Service  [Ends] *************/

        seatTypeDao.insert(seatType);


        return ResponseEntity.status(HttpStatus.OK).body(seatType);

    }


    @RequestMapping(value = "/update/{seatTypeId}",method = RequestMethod.POST)
    public ResponseEntity<?> updateSeatType(Authentication authentication,
                                            @Valid EditSeatTypeForm editSeatTypeForm, BindingResult result,
                                            @PathVariable Integer seatTypeId){
       AuthCredential authCredential = (AuthCredential) authentication.getPrincipal();
        System.out.println(editSeatTypeForm);

        SeatType seatType = seatTypeDao.getById(seatTypeId);

        editSeatTypeForm.setId(seatTypeId);

        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        if(seatType==null){
            serviceResponse.setValidationError("seatTypeId","No Seat Type found");
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
        editSeatTypeValidator.validate(editSeatTypeForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/


        seatType.setName(editSeatTypeForm.getName());
        seatType.setAdultPrice(editSeatTypeForm.getAdultPrice());
        seatType.setChildPrice(editSeatTypeForm.getChildPrice());
        seatType.setIsDefault(editSeatTypeForm.getIsDefault());
        seatType.setCreatedBy(authCredential.getId());
        /***************** Service  [Ends] *************/
        seatTypeDao.update(seatType);

        return ResponseEntity.status(HttpStatus.OK).body(seatType);

    }


    @RequestMapping(value = "/delete/{seatTypeId}",method = RequestMethod.DELETE)
    public boolean deleteSeatType(@PathVariable Integer seatTypeId){
        return  seatTypeDao.deleteSeatTpeById(seatTypeId);
    }
}
