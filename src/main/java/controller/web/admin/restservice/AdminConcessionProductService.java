package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionProductCategoryDao;
import dao.ConcessionProductDao;
import entity.ConcessionProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminConcessionProductService.createConcessionProduct.createConcessionProductForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/concession-product")
public class AdminConcessionProductService {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;
    @Autowired
    ConcessionProductDao concessionProductDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create (@Valid createConcessionProductForm createConcessionProductForm, BindingResult result, HttpServletRequest request){
        String errorMsg="Concession product successfully created";
        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }

            ConcessionProduct concessionProduct=new ConcessionProduct();


            concessionProduct.setName(createConcessionProductForm.getName());
            concessionProduct.setAnnotation(createConcessionProductForm.getAnnotation());
            concessionProduct.setDescription(createConcessionProductForm.getDescription());
            concessionProduct.setConcessionProductCategory(concessionProductCategoryDao.getById(createConcessionProductForm.getProductCategory()));
            concessionProduct.setUnit(createConcessionProductForm.getUnit());
            concessionProduct.setBuyingPrice(createConcessionProductForm.getBuyingPrice());
            concessionProduct.setSellingPrice(createConcessionProductForm.getSellingPrice());
            concessionProduct.setIsCombo(createConcessionProductForm.getIsCombo());
            concessionProduct.setIsPriceShift(createConcessionProductForm.getIsPriceShift());
            concessionProduct.setRemotePrint(createConcessionProductForm.getRemotePrint());
            concessionProduct.setCreatedBy(1);

            concessionProductDao.insert(concessionProduct);



        }catch (Exception e){

        }

        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));
    }

}
