package controller.web.admin.restservice;


import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import entity.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminDistributorService.createDistributor.createDistributorForm;
import validator.admin.AdminDistributorService.editDistributor.editDistributorForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/distributor")
public class AdminDistributorService {

    @Autowired
    DistributorDao distributorDao;


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create (@Valid createDistributorForm distributorForm, BindingResult result, HttpServletRequest request){
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

    @RequestMapping(value = "/edit/{distributorId}",method = RequestMethod.POST)
    public ResponseEntity<?> edit(@Valid editDistributorForm editDistributorForm,
                                  BindingResult bindingResult,
                                  HttpServletRequest request,
                                  @PathVariable Integer distributorId){
        String errorMsg="Distributor successfully updated";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());


        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();


            Distributor distributor=distributorDao.getById(distributorId);

            System.out.print(distributor);

            if(distributor==null){
                serviceResponse.setValidationError("distributorId","No distributor found");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            serviceResponse.bindValidationError(bindingResult);
            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }



            distributor.setId(distributorId);
            distributor.setName(editDistributorForm.getName());
            distributor.setPrimaryEmail(editDistributorForm.getPrimaryEmail());
            distributor.setSecondaryEmail(editDistributorForm.getSecondaryEmail());
            distributor.setPhone(editDistributorForm.getPhone());
            distributor.setAddress(editDistributorForm.getAddress());
            distributor.setDescription(editDistributorForm.getDescription());
            distributor.setUpdatedBy(1);
            distributor.setUpdatedAt(timestamp);

            System.out.println(distributor);

            distributorDao.update(distributor);

        }catch (Exception e){

        }


        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));

    }

    @RequestMapping(value = "/active-inactive/{distributorId}/{activationType}",method = RequestMethod.POST)
    public ResponseEntity<?> editStatus(@Valid editDistributorForm editDistributorForm,
                                        BindingResult result,
                                        @PathVariable Integer distributorId,
                                        @PathVariable String activationType){
        int status;
        System.out.println(activationType);

        if(activationType.equals("activate")){
            status = 1;
        }else  if(activationType.equals("deactivate")){
            status = 0;
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ServiceResponse.getMsg("Uri segment wrong"));
        }
        Distributor distributor=distributorDao.getById(distributorId);

        if(distributor == null) {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.setValidationError("distributorId", "No distributor found");

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);

        }

        distributor.setStatus(status);

        distributorDao.update(distributor);


        return ResponseEntity.status(HttpStatus.OK).body(distributor);


    }


}
