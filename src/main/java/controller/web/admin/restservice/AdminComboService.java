package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.ComboDao;
import dao.ComboDetailDao;
import dao.ConcessionProductDao;
import dao.SeatTypeDao;
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
import validator.admin.AdminComboService.createCombo.CreateComboForm;
import validator.admin.AdminComboService.createCombo.CreateComboValidator;
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
   ConcessionProductDao concessionProductDao;

   @Autowired
   ComboDetailDao comboDetailDao;

   @Autowired
    SeatTypeDao seatTypeDao;

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
            combo.setStatus(1);
            combo.setCreatedBy(1);

            comboDao.insert(combo);


            /**
             *  Combo product
             *  */

            List<ComboDetails> comboProductArray = new ArrayList<>();


            List<Integer> productsIds = createComboForm.getProductsIdArray();

            for (Integer productsId :productsIds){
                ConcessionProduct concessionProduct = concessionProductDao.getById(productsId);
                if(concessionProduct!=null){
                    ComboDetails comboDetail=new ComboDetails();
                    comboDetail.setComboId(combo.getId());
                    comboDetail.setComboProductType(createComboForm.getComboType());
                    comboDetail.setConcessionProductId(concessionProduct.getId());
                    comboDetail.setSeatTypeId(seatTypeDao.getById(createComboForm.getSeatTypeId()).getId());
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

            System.out.println(createComboForm);

            Combo combo=comboDao.getById(comboId);
            combo.setComboName(createComboForm.getComboName());
            combo.setDetails(createComboForm.getDetails());
            combo.setPrice(createComboForm.getPrice());
            combo.setStartDate(createComboForm.getFormattedStartDate());
            combo.setEndDate(createComboForm.getFormattedEndDate());
            combo.setComboType(createComboForm.getComboType());
            combo.setStatus(1);

            combo.setCreatedBy(1);

            comboDao.update(combo);


            /**
             *  Combo products
             *  */

            List<ComboDetails> comboProductArray = new ArrayList<>();

            List<Integer> productsIds = createComboForm.getProductsIdArray();
            for (Integer productsId :productsIds){
                ComboDetails comboProductd= comboDetailDao.getBycomboIdAndProductId(comboId,productsId);
                comboProductArray.add(comboProductd);
                if(comboProductd==null){
                    ConcessionProduct concessionProduct = concessionProductDao.getById(productsId);
                    if(concessionProduct!=null){
                        ComboDetails comboProduct=new ComboDetails();
                        comboProduct.setComboId(combo.getId());
                        comboProduct.setComboProductType(createComboForm.getComboType());
                        comboProduct.setConcessionProductId(concessionProduct.getId());
                        comboProductArray.add(comboProduct);
                    }
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

    @RequestMapping(value = "delcomboproduct/{comboproductId}",method = RequestMethod.GET)
    public ResponseEntity<?> delComboProduct(@Valid editDistributorForm editDistributorForm,
                                            BindingResult result,
                                            @PathVariable Integer comboproductId){

        ComboDetails comboProduct=comboDetailDao.getById(comboproductId);

        System.out.println(comboproductId);

        if(comboProduct == null) {
            ServiceResponse serviceResponse=ServiceResponse.getInstance();

            serviceResponse.setValidationError("ComboProductId", "No Combo Product found");

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
