package controller.web.admin.restservice;


import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminDistributorService.DistributorForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/distributor")
public class AdminDistributorService {
    @Autowired
    DistributorDao distributorDao;
    @RequestMapping(value = "create",method = RequestMethod.POST)
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
