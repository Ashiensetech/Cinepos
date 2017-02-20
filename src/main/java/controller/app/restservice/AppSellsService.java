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
import validator.admin.AdminSellsService.CreateSells.*;

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
    @Autowired
    ScreenSeatDao screenSeatDao;
    @Autowired
    CreateSellingValidator createSellingValidator;

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

//        createSellingValidator.validate(createOrMergeSellingForm.orderForm,result);
//
//        serviceResponse.bindValidationError(result);
//
//        if(serviceResponse.hasErrors()){
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
//        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        Terminal terminal= terminalDao.getById(createOrMergeSellingForm.orderForm.getTerminalId());
        AuthCredential authCredentialUser=authCredentialDao.getById(1);
        boolean isCombo=true;

        Sells sells=new Sells();
        sells.setSellingComment(createOrMergeSellingForm.orderForm.getSellingComment());
        sells.setCombo(true);
        sells.setTerminal(terminal);
        sells.setAuthCredential(authCredentialUser);

        sellsDao.insert(sells);


        Set<SellsDetails> sellDetails = new HashSet<>();
        Set<Ticket> ticketList = new HashSet<>();

        List<ConcessionProduct> updateConcessionProduct = new ArrayList<>();


        List<CartForm> sellsDetailsCart=createOrMergeSellingForm.orderForm.getCartForms();

        for(CartForm targetItem : sellsDetailsCart){

            SellsDetails sellsDetails=new SellsDetails();

            sellsDetails.setSellId(sells.getId());
            sellsDetails.setUserId(1);
            sellsDetails.setUnitSellingAmount(targetItem.getPrice());
            sellsDetails.setProductQuantity(targetItem.getProductQuantity());
            sellsDetails.setTicketQuantity(0);
            sellsDetails.setSellingType(targetItem.getSellingType());
            sellsDetails.setAuthCredential(authCredentialUser);


            if(targetItem.getSellingType().equals("product")){

                ConcessionProduct concessionProduct=concessionProductDao.getById(targetItem.getId());
                if(concessionProduct == null){
                    serviceResponse.setValidationError("sellProduct","Product not available");
                    break;
                }else if(concessionProduct.getUnit()<targetItem.getProductQuantity()){
                    serviceResponse.setValidationError("sellProduct","Not enough product are available");
                    break;
                } else{
                    isCombo=false;
                    sellsDetails.setConcessionProduct(concessionProduct);
                    concessionProduct.setUnit(concessionProduct.getUnit()-targetItem.getProductQuantity());
                    updateConcessionProduct.add(concessionProduct);
                }

            }else if (targetItem.getSellingType().equals("combo")){

                Combo combo=comboDao.getById(targetItem.getId());

                if(combo == null){
                    serviceResponse.setValidationError("sellProduct","Combo not available");
                    break;
                }

                for (ComboDetails tgtComboDetails:combo.getComboDetails()){

                    if(tgtComboDetails.getConcessionProductId()>0 && tgtComboDetails.getSeatTypeId()<=0){

                        ConcessionProduct concessionProduct=concessionProductDao.getById(tgtComboDetails.getConcessionProductId());

                        if(concessionProduct == null){
                            serviceResponse.setValidationError("sellProduct","Product not available");
                            break;
                        }else if(concessionProduct.getUnit() < tgtComboDetails.getProductQuantity()){
                            serviceResponse.setValidationError("sellProduct","Not enough product are available");
                            break;
                        }else{
                            //sellsDetails.setConcessionProduct(concessionProduct);
                            concessionProduct.setUnit(concessionProduct.getUnit()-tgtComboDetails.getProductQuantity());
                            updateConcessionProduct.add(concessionProduct);
                        }
                    }

                    if(tgtComboDetails.getSeatTypeId()>0 && tgtComboDetails.getConcessionProductId()>0){

                        ConcessionProduct concessionProduct=concessionProductDao.getById(tgtComboDetails.getConcessionProductId());

                        if(concessionProduct == null || concessionProduct.getUnit() < tgtComboDetails.getProductQuantity()){
                            serviceResponse.setValidationError("sellProduct","Product not available");
                            break;
                        }else{
                           // sellsDetails.setConcessionProduct(concessionProduct);
                            concessionProduct.setUnit(concessionProduct.getUnit()-tgtComboDetails.getProductQuantity());
                            updateConcessionProduct.add(concessionProduct);
                        }

                        Ticket ticket=ticketDao.getById(Long.valueOf(targetItem.getTicketId()));

                        if(ticket == null || ticket.getCurrentState().equals("BOOKED" )|| ticket.getCurrentState().equals("SOLD")){
                            serviceResponse.setValidationError("sellProduct","Ticket not available");
                            break;
                        }else{
                            String currentState="SOLD";
                            sellsDetails.setTicket(ticket);
                            ticket.setCurrentState(currentState);
                            ticketList.add(ticket);
                        }
                    }
                }
                isCombo=true;
                sellsDetails.setCombo(combo);

            }else{

                Ticket ticket=ticketDao.getById(Long.valueOf(targetItem.getId()));

                if(ticket == null){
                    serviceResponse.setValidationError("sellProduct","Ticket not available");
                    break;
                } else if(ticket.getCurrentState().equals("SOLD")){
                    serviceResponse.setValidationError("sellProduct","Ticket is sold");
                    break;
                }else if(ticket.getCurrentState().equals("BOOKED")){
                    serviceResponse.setValidationError("sellProduct","Ticket is booked");
                    break;
                } else{
                    String currentState="SOLD";
                    sellsDetails.setTicket(ticket);
                    ticket.setCurrentState(currentState);
                    ticketList.add(ticket);

                    isCombo=false;
                }
            }

            totalPrice+=targetItem.getPrice();
            totalQuantity+=targetItem.getProductQuantity();

            sellDetails.add(sellsDetails);
        }

        if(serviceResponse.hasErrors()){
            sellsDao.delete(sells);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        if(sellDetailsDao.insertOrUpdate(sellDetails)){
            sells.setSellDetails(sellDetails);

            Sells sellsUpdate=sellsDao.getById(sells.getId());
            sellsUpdate.setQuantity(totalQuantity);
            sellsUpdate.setStatus(true);
            sellsUpdate.setCombo(isCombo);
            sellsUpdate.setSellingAmount(totalPrice);

            sellsDao.update(sellsUpdate);


            for(Ticket ticket:ticketList){
                ticketDao.insertOrUpdate(ticket);
            }

            for(ConcessionProduct productTgt:updateConcessionProduct){
                concessionProductDao.update(productTgt);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(errorMsg);
    }
}
