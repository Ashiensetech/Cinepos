package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.*;
import entity.*;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import utility.ServiceResponse;
import validator.admin.AdminSellsService.CreateSells.CartForm;
import validator.admin.AdminSellsService.CreateSells.CreateOrMergeSellingForm;
import validator.admin.AdminSellsService.CreateSells.CreateOrMergeSellingFormValidator;
import validator.admin.AdminSellsService.CreateSells.OrderForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.*;


@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/sells")
public class AppSellsService {
    @Autowired
    SellsDao sellsDao;
    @Autowired
    CreateOrMergeSellingFormValidator createOrMergeSellingFormValidator;
    @Autowired
    SellDetailsDao sellDetailsDao;
    @Autowired
    ConcessionProductDao concessionProductDao;
    @Autowired
    ComboDao comboDao;
    @Autowired
    SeatTypeDao seatTypeDao;
    @Autowired
    TerminalDao terminalDao;
    @Autowired
    AuthCredentialDao authCredentialDao;
    @Autowired
    TicketDao ticketDao;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(Authentication authentication,
                                    @Valid CreateOrMergeSellingForm createOrMergeSellingForm,
                                    BindingResult result,
                                    HttpServletRequest request) {
        String errorMsg="Order create successfully";
        float totalPrice=0;
        int totalQuantity=0;


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


       Terminal terminal= terminalDao.getById(createOrMergeSellingForm.orderForm.getTerminalId());
       AuthCredential authCredentialUser=authCredentialDao.getById(1);

        Sells sells=new Sells();
        sells.setSellingComment("Dummy comment for sells");
        sells.setCombo(true);
        sells.setTerminal(terminal);
        sells.setStatus(true);
        sells.setAuthCredential(authCredentialUser);

        sellsDao.insert(sells);


        List<SellsDetails> sellDetailsArray = new ArrayList<>();
        Set<SellsDetails> sellDetails = new HashSet<>();
        Set<Ticket> ticketList = new HashSet<>();

        List<CartForm> sellsDetailsCart=createOrMergeSellingForm.orderForm.getCartForms();

        for(CartForm targetItem : sellsDetailsCart){

            SellsDetails sellsDetails=new SellsDetails();

            sellsDetails.setSellId(sells.getId());
            sellsDetails.setUserId(1);
            sellsDetails.setUnitSellingAmount(targetItem.getPrice());
            sellsDetails.setQuantity(targetItem.getQuantity());
            sellsDetails.setSellingType(targetItem.getSellingType());
            sellsDetails.setAuthCredential(authCredentialUser);


            if(targetItem.getSellingType().equals("product")){

                ConcessionProduct concessionProduct=concessionProductDao.getById(targetItem.getId());
                if(concessionProduct == null){
                    serviceResponse.setValidationError("concessionProduct","Product not available");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
                }
                sellsDetails.setConcessionProduct(concessionProduct);

            }else if (targetItem.getSellingType().equals("combo")){

                Combo combo=comboDao.getById(targetItem.getId());
                if(combo == null){
                    serviceResponse.setValidationError("concessionProduct","Combo not available");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
                }
                sellsDetails.setCombo(combo);

            }else{

                Ticket ticket=ticketDao.getById(Long.valueOf(targetItem.getId()));
                if(ticket == null){
                    serviceResponse.setValidationError("concessionProduct","Ticket not available");
                    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
                }

                 String currentState="AVAILABLE";
                 sellsDetails.setTicket(ticket);
                 ticket.setCurrentState(currentState);
                 ticketList.add(ticket);
            }

            totalPrice+=targetItem.getPrice();
            totalQuantity+=targetItem.getQuantity();

            sellDetails.add(sellsDetails);
        }


        if(sellDetailsDao.insertOrUpdate(sellDetails)){
            sells.setSellDetails(sellDetails);

            Sells sellsUpdate=sellsDao.getById(sells.getId());
            sellsUpdate.setQuantity(totalQuantity);
            sellsUpdate.setSellingAmount(totalPrice);

            sellsDao.update(sellsUpdate);
        }else{
            sellsDao.delete(sells);
            sellDetailsDao.delete(sellDetails);
        }



        return ResponseEntity.status(HttpStatus.OK).body(errorMsg);

    }
}
