package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionProductCategoryDao;
import entity.ConcessionProductCategory;
import entity.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utility.ServiceResponse;
import validator.admin.AdminConcessionProductCategoryService.createProductCategory.createProductCategoryForm;
import validator.admin.AdminConcessionProductCategoryService.editProductCategory.editProductCategoryForm;
import validator.admin.AdminDistributorService.createDistributor.createDistributorForm;
import validator.admin.AdminDistributorService.editDistributor.editDistributorForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/13/2017.
 */
@Repository
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/product-category")

public class AdminProductCategoryService {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;



    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create (@Valid createProductCategoryForm createProductCategoryForm, BindingResult result, HttpServletRequest request){
        String errorMsg="Concession Product Category successfully created";
        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            ConcessionProductCategory concessionProductCategory=new ConcessionProductCategory();

            concessionProductCategory.setName(createProductCategoryForm.getName());
            concessionProductCategory.setStatus(1);
            concessionProductCategory.setCreatedBy(1);
            concessionProductCategoryDao.insert(concessionProductCategory);

        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));
    }

    @RequestMapping(value = "/edit/{productCategoryId}",method = RequestMethod.POST)
    public ResponseEntity<?> edit(@Valid editProductCategoryForm editProductCategoryForm,
                                  BindingResult bindingResult,
                                  HttpServletRequest request,
                                  @PathVariable Integer productCategoryId){
        String errorMsg="Concession Product Category successfully updated";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());


        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();


            ConcessionProductCategory concessionProductCategory=concessionProductCategoryDao.getById(productCategoryId);

            System.out.print(concessionProductCategory);

            if(concessionProductCategory==null){
                serviceResponse.setValidationError("productCategoryId","No distributor found");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            serviceResponse.bindValidationError(bindingResult);
            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }



            concessionProductCategory.setId(productCategoryId);
            concessionProductCategory.setName(editProductCategoryForm.getName());

            System.out.println(concessionProductCategory);

            concessionProductCategoryDao.update(concessionProductCategory);

        }catch (Exception e){

        }


        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));

    }

    @RequestMapping(value = "/active-inactive/{productCategoryId}/{activationType}",method = RequestMethod.POST)
    public ResponseEntity<?> editStatus(@Valid editProductCategoryForm editProductCategoryForm,
                                        BindingResult result,
                                        @PathVariable Integer productCategoryId,
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
        ConcessionProductCategory concessionProductCategory=concessionProductCategoryDao.getById(productCategoryId);

        if(concessionProductCategory == null) {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.setValidationError("productCategoryId", "No distributor found");

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);

        }

        concessionProductCategory.setStatus(status);

        concessionProductCategoryDao.update(concessionProductCategory);


        return ResponseEntity.status(HttpStatus.OK).body(concessionProductCategory);


    }



}
