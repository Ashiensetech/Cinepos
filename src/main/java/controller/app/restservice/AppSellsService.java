package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.SellsDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utility.ServiceResponse;
import validator.admin.AdminSellsService.CreateSells.CreateOrMergeSellingForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 2/2/2017.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/sells")
public class AppSellsService {
    @Autowired
    SellsDao sellsDao;

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

         //String stringToParse="";
        //JSONParser parser = new JSONParser();
        //JSONObject json = (JSONObject) parser.parse(createOrMergeSellingForm.getOrdersJson());

        System.out.println(createOrMergeSellingForm.getOrdersJson());

        return ResponseEntity.status(HttpStatus.OK).body(createOrMergeSellingForm.getOrdersJson());

    }
}
