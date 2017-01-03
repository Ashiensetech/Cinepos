package controller.web.admin.restservice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utility.ServiceResponse;
import validator.admin.AdminDistributorService.DistributorForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public class AdminDistributorService {
    @RequestMapping(value = "post-create_distributor",method = RequestMethod.POST)
    public ResponseEntity<?> create (@Valid DistributorForm distributorForm, BindingResult result, HttpServletRequest request){
         String errorMsg="Something Wrong";
         try{
             ServiceResponse serviceResponse=ServiceResponse.getInstance();
             serviceResponse.bindValidationError(result);

             if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
             }


         }catch (Exception e){

         }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));
    }


}
