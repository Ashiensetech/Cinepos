package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.SellsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utility.ServiceResponse;
import validator.admin.AdminSellsService.CreateSells.CreateOrMergeSellingForm;
import validator.admin.AdminSellsService.CreateSells.CreateOrMergeSellingFormValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Sarwar on 2/2/2017.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/sells")
public class AppSellsService {
    @Autowired
    SellsDao sellsDao;
    @Autowired
    CreateOrMergeSellingFormValidator createOrMergeSellingFormValidator;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid CreateOrMergeSellingForm createOrMergeSellingForm,
                                    BindingResult result,
                                    HttpServletRequest request) {
        String errorMsg="Order create successfully";

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
        createOrMergeSellingFormValidator.validate(createOrMergeSellingForm,result);
        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        //System.out.println(createOrMergeSellingForm.orderForm.getTerminalId()+"Hello");
        System.out.println(createOrMergeSellingForm.orderForm.getCartForms());


        System.out.print(createOrMergeSellingForm.orderForm.getCartForms().size());




        //System.out.println(createOrMergeSellingForm.getOrdersJson());

        return ResponseEntity.status(HttpStatus.OK).body(createOrMergeSellingForm.getOrdersJson());

    }
}
