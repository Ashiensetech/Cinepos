package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.SellDetailsDao;
import dao.SellsDao;
import entity.Sells;
import entity.SellsDetails;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utility.ServiceResponse;
import validator.admin.AdminSellsService.CreateSells.CartForm;
import validator.admin.AdminSellsService.CreateSells.CreateOrMergeSellingForm;
import validator.admin.AdminSellsService.CreateSells.CreateOrMergeSellingFormValidator;
import validator.admin.AdminSellsService.CreateSells.OrderForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    SellDetailsDao sellDetailsDao;

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

        Sells sells=new Sells();
        sells.setSellingAmount(1.5);
        sells.setSellingComment("Testing comment for sells");
        sells.setCombo(true);
        sells.setQuantity(5);
        sells.setTerminalId(createOrMergeSellingForm.orderForm.getTerminalId());
        sells.setStatus(true);
        sells.setCreatedBy(1);

        sellsDao.insert(sells);


        List<SellsDetails> sellDetailsArray = new ArrayList<>();

        List<CartForm> sellsDetailsCart=createOrMergeSellingForm.orderForm.getCartForms();

        for(CartForm targetItem : sellsDetailsCart){

            SellsDetails sellsDetails=new SellsDetails();

            sellsDetails.setSellId(sells.getId());
            sellsDetails.setConcessionProductId(targetItem.getId());
            //sellsDetails.setComboId(1);
           // sellsDetails.setSeatTypeId(1);
            sellsDetails.setUserId(1);
            sellsDetails.setUnitSellingAmount(targetItem.getPrice());
            sellsDetails.setQuantity(targetItem.getQuantity());
            sellsDetails.setSellingType(targetItem.getSellingType());
            sellsDetails.setCreatedBy(1);

            sellDetailsDao.insert(sellsDetails);
        }

        return ResponseEntity.status(HttpStatus.OK).body(createOrMergeSellingForm.getOrdersJson());

    }
}
