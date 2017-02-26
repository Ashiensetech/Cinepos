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
import validator.admin.restservice.sells.create.CartForm;
import validator.admin.restservice.sells.create.CreateOrMergeSellingForm;
import validator.admin.restservice.sells.create.CreateOrMergeSellingFormValidator;
import validator.admin.restservice.sells.create.CreateSellingValidator;

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

    private final static String TICKET_AVAILABLE="AVAILABLE";
    private final static String TICKET_BOOKED="BOOKED";
    private final static String TICKET_SOLD="SOLD";

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

        if(terminalId == null){
            serviceResponse.setValidationError("screenId","Screen Id required");
        }
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Business logic Validation
         * */


        Terminal terminal = terminalDao.getById(terminalId);

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
        sells.setTerminal(terminal);
        sells.setSaleBy(authCredentialUser);

        sellsDao.insert(sells);


        Set<SellsDetails> sellDetailsList = new HashSet<>();
        Set<Ticket> ticketList = new HashSet<>();

        List<ConcessionProduct> updateConcessionProduct = new ArrayList<>();


        List<CartForm> sellsDetailsCart=createOrMergeSellingForm.orderForm.getCartForms();
        boolean ticketSellFlag = false;

        if(sellsDetailsCart==null || sellsDetailsCart.size()==0){
            serviceResponse.setValidationError("ordersJson","No product found in cart");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

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

                if(targetItem.getId()==null){
                    serviceResponse.setValidationError("id","ID required");
                    break;
                }

                ConcessionProduct concessionProduct=concessionProductDao.getById(targetItem.getId());

                if(concessionProduct == null){
                    serviceResponse.setValidationError("id","No product found with ID :"+targetItem.getId());
                    break;
                }else if(concessionProduct.getUnit()<targetItem.getProductQuantity()){
                    serviceResponse.setValidationError("id","Not enough product are available");
                    break;
                }else if(concessionProduct.getSellingPrice() != targetItem.getPrice()){
                    serviceResponse.setValidationError("price","Price mismatched");
                    break;
                } else{
                    isCombo=false;
                    sellsDetails.setConcessionProduct(concessionProduct);
                    concessionProduct.setUnit(concessionProduct.getUnit() - targetItem.getProductQuantity());
                    updateConcessionProduct.add(concessionProduct);
                    sellsDetails.setProductQuantity(targetItem.getProductQuantity());
                }

            }else if (targetItem.getSellingType().equals("combo")){
                /**
                 * If sale is combo
                 * */
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
                    }else if(tgtComboDetails.getSeatTypeId()>0 && tgtComboDetails.getConcessionProductId()>0){
                        /**
                         * Combo details . if combo details is seat type [ For ticket combo perpose ]
                         * */
                        ticketSellFlag = true;

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
                        } else if(ticket.getCurrentState().equals(TICKET_SOLD)){
                            serviceResponse.setValidationError("ticketId","Ticket is sold");
                            break;
                        }else if(ticket.getCurrentState().equals(TICKET_BOOKED)){
                            serviceResponse.setValidationError("ticketId","Ticket is booked");
                            break;
                        } else{
                            sellsDetails.setTicket(ticket);
                            ticket.setCurrentState(TICKET_SOLD);
                            ticketList.add(ticket);
                        }
                    }
                }
                isCombo=true;
                sellsDetails.setCombo(combo);
                sellsDetails.setProductQuantity(1);
            }else if (targetItem.getSellingType().equals("ticket")){
            /**
             * If only ticket
             * */
                ticketSellFlag = true;
                Ticket ticket=ticketDao.getById(Long.valueOf(targetItem.getId()));

                if(ticket == null){
                    serviceResponse.setValidationError("ticketId","Ticket not available");
                    break;
                } else if(ticket.getCurrentState().equals(TICKET_SOLD)){
                    serviceResponse.setValidationError("ticketId","Ticket is sold");
                    break;
                }else if(ticket.getCurrentState().equals(TICKET_BOOKED)){
                    serviceResponse.setValidationError("ticketId","Ticket is booked");
                    break;
                } else{
                    ticket.setCurrentState(TICKET_SOLD);
                    ticketList.add(ticket);

                    isCombo=false;
                    /**
                    * Adding ticket in sale details
                    * */
                    sellsDetails.setTicket(ticket);
                    sellsDetails.setProductQuantity(1);
                }
            }else{
                serviceResponse.setValidationError("sellingType","Selling type is missing");
                break;
            }

            totalPrice+=targetItem.getPrice();
            totalQuantity+=targetItem.getProductQuantity();

            sellDetailsList.add(sellsDetails);
        }




        Screen screen = null;
        /**
         * Screen required If and only if ticket is sold
         * */

        if(ticketSellFlag){
            if(screenId == null){
                serviceResponse.setValidationError("screenId","Screen Id required");
            }else{
                screen = screenDao.getSummarById(screenId);

                if(screen==null){
                    serviceResponse.setValidationError("screenId","No screen found with screen Id :"+ screenId);
                }
            }
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

        /**
         * Insert sell details
         * */
        boolean saleDetailsInsertFlag = sellDetailsDao.insertOrUpdate(sellDetailsList);
        System.out.println(sellDetailsList);
        if(saleDetailsInsertFlag){
            /**
             * Update sell
            * */
            if(screen!=null)sells.setScreen(screen);
            sells.setProductQuantity(totalQuantity);
            sells.setStatus(true);
            sells.setCombo(isCombo);
            sells.setSellingAmount(totalPrice);
            sellsDao.update(sells);

            /**
             * Update Tickets after setting current status sold
             * */
            for(Ticket ticket:ticketList){
                ticketDao.insertOrUpdate(ticket);
            }

            /**
             * Update Concession product
             * after decreasing quantity
             * */
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
