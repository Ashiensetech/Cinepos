package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminComboService.createCombo.*;
import validator.admin.AdminDistributorService.editDistributor.editDistributorForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 1/19/2017.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/combo")
public class AdminComboService {
   @Autowired
    ComboDao comboDao;

   @Autowired
   CreateComboValidator createComboValidator;

   @Autowired
   ComboProductValidator comboProductValidator;

   @Autowired
   ConcessionProductDao concessionProductDao;

   @Autowired
   ComboDetailDao comboDetailDao;

   @Autowired
    SeatTypeDao seatTypeDao;
   @Autowired
    SellDetailsDao sellDetailsDao;


    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid CreateComboForm createComboForm,
                                    BindingResult result,
                                    HttpServletRequest request){
        String errorMsg="Combo create successfully";

        try {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if (serviceResponse.hasErrors()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            createComboValidator.validate(createComboForm,result);
            serviceResponse.bindValidationError(result);

            if (serviceResponse.hasErrors()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }



            Combo combo=new Combo();
            combo.setComboName(createComboForm.getComboName());
            combo.setDetails(createComboForm.getDetails());
            combo.setPrice(createComboForm.getPrice());
            combo.setStartDate(createComboForm.getFormattedStartDate());
            combo.setEndDate(createComboForm.getFormattedEndDate());
            combo.setComboType(createComboForm.getComboType());
            combo.setSeatTypeId(seatTypeDao.getById(createComboForm.getSeatTypeId()).getId());
            combo.setStatus(1);
            combo.setCreatedBy(1);

            comboDao.insert(combo);

            List<ComboDetails> comboProductArray = new ArrayList<>();

            /**
             * Get Combo Product
             * */
            List<ComboProduct>  comboProductsList= createComboForm.getComboProduct();


            for (ComboProduct tgtComboProduct :comboProductsList){

                ConcessionProduct concessionProduct = concessionProductDao.getById(tgtComboProduct.getProductId());

                if(concessionProduct!=null){

                        ComboDetails comboDetail=new ComboDetails();
                        comboDetail.setComboId(combo.getId());
                        comboDetail.setComboProductType(createComboForm.getComboType());
                        comboDetail.setConcessionProductId(concessionProduct.getId());
                        comboDetail.setProductQuantity(tgtComboProduct.getQuantity());
                        comboDetail.setTicketQuantity(1);
                        comboDetail.setSeatTypeId(combo.getSeatTypeId());
                        comboDetail.setCreatedBy(1);
                        comboProductArray.add(comboDetail);
                }
            }

            combo.setComboDetails(comboProductArray);
            /**
             * Updating Combo
             * */
            comboDao.update(combo);



        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));



    }

    @RequestMapping(value = "/edit/{comboId}",method = RequestMethod.POST)
    public ResponseEntity<?> edit(@Valid CreateComboForm createComboForm,
                                    BindingResult result,
                                    HttpServletRequest request,
                                    @PathVariable Integer comboId){
        String errorMsg="Combo updated successfully";
        try {

            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if (serviceResponse.hasErrors()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            createComboValidator.validate(createComboForm,result);
            serviceResponse.bindValidationError(result);

            if (serviceResponse.hasErrors()) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }


            Combo combo=comboDao.getById(comboId);
            combo.setComboName(createComboForm.getComboName());
            combo.setDetails(createComboForm.getDetails());
            combo.setPrice(createComboForm.getPrice());
            combo.setStartDate(createComboForm.getFormattedStartDate());
            combo.setEndDate(createComboForm.getFormattedEndDate());
            combo.setComboType(createComboForm.getComboType());
            combo.setSeatTypeId(createComboForm.getSeatTypeId());
            combo.setStatus(1);

            combo.setCreatedBy(1);

            /**
             *  Combo products
             *  */

            List<ComboDetails> comboDetailArray = new ArrayList<>();
            /**
             * Get Combo Product
             * */
            List<ComboProduct>  comboProductsList= createComboForm.getComboProduct();

            for (ComboProduct tgtComboProduct :comboProductsList){

                ConcessionProduct concessionProduct = concessionProductDao.getById(tgtComboProduct.getProductId());

                ComboDetails comboDetails= comboDetailDao.getByComboIdAndProductId(comboId,tgtComboProduct.getProductId());

                if(comboDetails==null){

                    if(concessionProduct!=null){

                        ComboDetails comboDetail=new ComboDetails();
                        comboDetail.setComboId(comboId);
                        comboDetail.setComboProductType(createComboForm.getComboType());
                        comboDetail.setConcessionProductId(concessionProduct.getId());
                        comboDetail.setProductQuantity(tgtComboProduct.getQuantity());
                        comboDetail.setTicketQuantity(1);
                        comboDetail.setSeatTypeId(combo.getSeatTypeId());
                        comboDetail.setCreatedBy(1);

                        comboDetailArray.add(comboDetail);

                    }

                }else{

                    if(concessionProduct!=null){


                        comboDetails.setComboId(combo.getId());
                        comboDetails.setComboProductType(createComboForm.getComboType());
                        comboDetails.setConcessionProductId(concessionProduct.getId());
                        comboDetails.setProductQuantity(tgtComboProduct.getQuantity());
                        comboDetails.setTicketQuantity(1);
                        comboDetails.setSeatTypeId(combo.getSeatTypeId());
                        comboDetails.setCreatedBy(1);
                        comboDetailArray.add(comboDetails);
                    }
                }
            }

            combo.setComboDetails(comboDetailArray);

            /**
             * Updating Combo
             * */
            comboDao.update(combo);

        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));



    }

    @RequestMapping(value = "delete/{comboDetailsId}",method = RequestMethod.GET)
    public ResponseEntity<?> delComboProduct(@Valid editDistributorForm editDistributorForm,
                                            BindingResult result,
                                            @PathVariable Integer comboDetailsId){

        ServiceResponse serviceResponse=ServiceResponse.getInstance();

        ComboDetails comboProduct=comboDetailDao.getById(comboDetailsId);

       if(comboProduct == null) {
            serviceResponse.setValidationError("ComboProductId", "No combo product found");
           return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);
       }

       SellsDetails sellsDetails=sellDetailsDao.getByComboId(comboProduct.getComboId());

        if(sellsDetails!=null){
            serviceResponse.setValidationError("ComboProductId", "You are not eligible to delete this product.");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);
        }


        comboDetailDao.deleteComboProduct(comboProduct);


        return ResponseEntity.status(HttpStatus.OK).body(comboProduct);

    }


    @RequestMapping(value = "/active-inactive/{distributorId}/{activationType}",method = RequestMethod.POST)
    public ResponseEntity<?> editStatus(@Valid editDistributorForm editDistributorForm,
                                        BindingResult result,
                                        @PathVariable Integer distributorId,
                                        @PathVariable String activationType){
        int status;
        if(activationType.equals("activate")){
            status = 1;
        }else  if(activationType.equals("deactivate")){
            status = 0;
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ServiceResponse.getMsg("Uri segment wrong"));
        }
        Combo combo=comboDao.getById(distributorId);

        if(combo == null) {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.setValidationError("ComboId", "No distributor found");

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);

        }

        combo.setStatus(status);

        comboDao.update(combo);


        return ResponseEntity.status(HttpStatus.OK).body(combo);


    }

}
