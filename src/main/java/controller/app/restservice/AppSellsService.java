package controller.app.restservice;

import com.fasterxml.jackson.annotation.JsonView;
import controller.app.AppUriPreFix;
import dao.*;
import entity.*;
import entity.app.jsonview.sells.SellsJsonView;
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
    ScreenDao screenDao;

    @Autowired
    CreateSellingValidator createSellingValidator;

    @JsonView(SellsJsonView.Summary.class)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public synchronized ResponseEntity<?> create(Authentication authentication,
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

        /***************** Validation  [End] *************/
        Integer screenId = createOrMergeSellingForm.getOrderForm().getScreenId();
        Integer terminalId = createOrMergeSellingForm.getOrderForm().getTerminalId();

        /**
         * Basic Validation
         * */

        if(screenId == null){
            serviceResponse.setValidationError("screenId","Screen Id required");
        }
        if(terminalId == null){
            serviceResponse.setValidationError("screenId","Screen Id required");
        }
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Business logic Validation
         * */

        Screen screen = screenDao.getSummarById(screenId);
        Terminal terminal = terminalDao.getById(terminalId);

        if(screen==null){
            serviceResponse.setValidationError("screenId","No screen found with screen Id :"+ screenId);
        }

        if(terminal==null){
            serviceResponse.setValidationError("terminalId","No terminal found with terminal Id :"+ terminalId);
        }

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /***************** Service  [Start] *************/
        AuthCredential authCredentialUser=authCredentialDao.getById(1);
        boolean isCombo=true;

        Sells sells=new Sells();
        sells.setSellingComment(createOrMergeSellingForm.orderForm.getSellingComment());
        sells.setCombo(true);
        sells.setScreen(screen);
        sells.setTerminal(terminal);
        sells.setSaleBy(authCredentialUser);

        sellsDao.insert(sells);


        Set<SellsDetails> sellDetailsList = new HashSet<>();
        Set<Ticket> ticketList = new HashSet<>();

        List<ConcessionProduct> updateConcessionProduct = new ArrayList<>();


        List<CartForm> sellsDetailsCart=createOrMergeSellingForm.orderForm.getCartForms();
        /**
         * Array of product or ticket or combo , JSON Array from request
        * */
        for(CartForm targetItem : sellsDetailsCart){

            SellsDetails sellsDetails=new SellsDetails();

            sellsDetails.setSellId(sells.getId());
            sellsDetails.setUserId(1);
            sellsDetails.setUnitSellingAmount(targetItem.getPrice());
            sellsDetails.setProductQuantity(targetItem.getProductQuantity());
            sellsDetails.setTicketQuantity(0);
            sellsDetails.setSellingType(targetItem.getSellingType());
            sellsDetails.setAuthCredential(authCredentialUser);

            /**
             * If sale is product . Product with quantity
             * */

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
                    concessionProduct.setUnit(concessionProduct.getUnit() - targetItem.getProductQuantity());
                    updateConcessionProduct.add(concessionProduct);
                    sellsDetails.setProductQuantity(targetItem.getProductQuantity());
                }

            }
            /**
             * If sale is combo
             * */
            else if (targetItem.getSellingType().equals("combo")){

                Combo combo=comboDao.getById(targetItem.getId());

                if(combo == null){
                    serviceResponse.setValidationError("sellProduct","Combo not available");
                    break;
                }
                /**
                 * Combo details . Contains all the product with quantity
                 * */
                for (ComboDetails tgtComboDetails:combo.getComboDetails()){

                    /**
                     * Combo details . if combo details is product
                     * */
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

                    /**
                     * Combo details . if combo details is seat type [ For ticket combo perpose ]
                     * */
                    else if(tgtComboDetails.getSeatTypeId()>0 && tgtComboDetails.getConcessionProductId()>0){

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

                        /**
                         * Ticket status check
                         * Only AVAILABLE can be sold
                         * */

                        if(ticket == null){
                            serviceResponse.setValidationError("ticketId","Ticket not available");
                            break;
                        } else if(ticket.getCurrentState().equals("SOLD")){
                            serviceResponse.setValidationError("ticketId","Ticket is sold");
                            break;
                        }else if(ticket.getCurrentState().equals("BOOKED")){
                            serviceResponse.setValidationError("ticketId","Ticket is booked");
                            break;
                        } else{
                            String currentState="SOLD";
                            sellsDetails.setTicket(ticket);
                            ticket.setCurrentState(currentState);
                            ticketList.add(ticket);
                        }
                    }
                }
                isCombo=true;
                sellsDetails.setCombo(combo);
                sellsDetails.setProductQuantity(1);
            }
            /**
             * If only ticket
             * */

            else{

                Ticket ticket=ticketDao.getById(Long.valueOf(targetItem.getId()));

                if(ticket == null){
                    serviceResponse.setValidationError("ticketId","Ticket not available");
                    break;
                } else if(ticket.getCurrentState().equals("SOLD")){
                    serviceResponse.setValidationError("ticketId","Ticket is sold");
                    break;
                }else if(ticket.getCurrentState().equals("BOOKED")){
                    serviceResponse.setValidationError("ticketId","Ticket is booked");
                    break;
                } else{
                    String currentState="SOLD";
                    ticket.setCurrentState(currentState);
                    ticketList.add(ticket);

                    isCombo=false;
                    /**
                    * Adding ticket in sale details
                    * */
                    sellsDetails.setTicket(ticket);
                    sellsDetails.setProductQuantity(1);
                }
            }

            totalPrice+=targetItem.getPrice();
            totalQuantity+=targetItem.getProductQuantity();

            sellDetailsList.add(sellsDetails);
        }

        /**
         * Checking Business validation
         * */

        if(serviceResponse.hasErrors()){
            sellsDao.delete(sells);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * JPA db execution
         * */

        if(sellDetailsDao.insertOrUpdate(sellDetailsList)){
            sells.setSellDetails(sellDetailsList);

            Sells sellsUpdate=sellsDao.getById(sells.getId());
            sellsUpdate.setProductQuantity(totalQuantity);
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
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ServiceResponse.getMsg("Internal Server Error..!!! Notify Backend BOSS"));
        }
        sells = sellsDao.getById(sells.getId());
        return ResponseEntity.status(HttpStatus.OK).body(sells);
    }
}
