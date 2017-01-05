package controller.web.admin.restservice;


import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import entity.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.security.Timestamp;

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/distributor")
public class AdminDistributorService {
    @Autowired
    DistributorDao distributorDao;

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public ResponseEntity<?> create (@Valid DistributorForm distributorForm, BindingResult result, HttpServletRequest request){
         String errorMsg="Distributor successfully created";
         try{
             ServiceResponse serviceResponse=ServiceResponse.getInstance();
             serviceResponse.bindValidationError(result);

             if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
             }

             Distributor distributor=new Distributor();

             distributor.setName(distributorForm.getName());
             distributor.setPrimaryEmail(distributorForm.getPrimaryEmail());
             distributor.setSecondaryEmail(distributorForm.getSecondaryEmail());
             distributor.setPhone(distributorForm.getPhone());
             distributor.setAddress(distributorForm.getAddress());
             distributor.setDescription(distributorForm.getDescription());
             distributor.setStatus(1);
             distributor.setCreatedBy(1);

             distributorDao.insert(distributor);

         }catch (Exception e){

         }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public ResponseEntity<?> edit(@Valid DistributorForm distributorForm,BindingResult bindingResult,HttpServletRequest request){
        String errorMsg="Distributor successfully updated";

        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(bindingResult);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            Distributor distributor=new Distributor();
            distributor.setId(4);
            distributor.setName(distributorForm.getName());
            distributor.setPrimaryEmail(distributorForm.getPrimaryEmail());
            distributor.setSecondaryEmail(distributorForm.getSecondaryEmail());
            distributor.setPhone(distributorForm.getPhone());
            distributor.setAddress(distributorForm.getAddress());
            distributor.setDescription(distributorForm.getDescription());
            distributor.setStatus(1);
            distributor.setCreatedBy(1);

            distributorDao.update(distributor);

        }catch (Exception e){

        }


        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));

    }


}
