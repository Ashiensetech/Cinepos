package validator.admin.AdminSellsService.CreateSells;

import dao.ComboDao;
import dao.ConcessionProductDao;
import dao.TicketDao;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.admin.AdminConcessionPriceShiftService.editConcessionPriceShift.EditConcessionPriceShiftForm;

import javax.xml.ws.ServiceMode;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Sarwar on 2/15/2017.
 */
@Service
public class CreateSellingValidator implements Validator {
    @Autowired
    ConcessionProductDao concessionProductDao;
    @Autowired
    TicketDao ticketDao;
    @Autowired
    ComboDao comboDao;

    @Override
    public void validate(Object obj, Errors errors) {
        OrderForm orderForm = (OrderForm) obj;

        List<CartForm> sellsDetailsCart=orderForm.getCartForms();

        for(CartForm targetItem : sellsDetailsCart){

            if(targetItem.getSellingType().equals("product")){

                ConcessionProduct concessionProduct=concessionProductDao.getById(targetItem.getId());
                if(concessionProduct == null || concessionProduct.getUnit()<targetItem.getQuantity()){
                    errors.rejectValue("sellProduct","Product not available");
                    break;
                }

            }else if (targetItem.getSellingType().equals("combo")){

                Combo combo=comboDao.getById(targetItem.getId());
                if(combo == null){

                    errors.rejectValue("sellProduct","Combo not available");
                }
                for (ComboDetails tgtComboDetails:combo.getComboDetails()){

                    if(tgtComboDetails.getConcessionProductId()>0 && (tgtComboDetails.getSeatTypeId()==0 || tgtComboDetails.getSeatTypeId()<0) ){

                        ConcessionProduct concessionProduct=concessionProductDao.getById(tgtComboDetails.getConcessionProductId());

                        if(concessionProduct == null || concessionProduct.getUnit() < tgtComboDetails.getQuantity()){
                            errors.rejectValue("sellProduct","Product not available");
                            break;
                        }
                    }
                    if(tgtComboDetails.getSeatTypeId()>0 && tgtComboDetails.getConcessionProductId()>0){

                        ConcessionProduct concessionProduct=concessionProductDao.getById(tgtComboDetails.getConcessionProductId());

                        if(concessionProduct == null || concessionProduct.getUnit() < tgtComboDetails.getQuantity()){
                            errors.rejectValue("sellProduct","Product not available");
                            break;
                        }

                        Ticket ticket=ticketDao.getById(Long.valueOf(targetItem.getTicketId()));

                        if(ticket == null || ticket.getCurrentState().equals("BOOKED" )|| ticket.getCurrentState().equals("SOLD")){
                            errors.rejectValue("sellProduct","Ticket not available");
                            break;
                        }
                    }


                }
            }else{

                Ticket ticket=ticketDao.getById(Long.valueOf(targetItem.getId()));

                if(ticket == null || ticket.getCurrentState().equals("BOOKED" )|| ticket.getCurrentState().equals("SOLD")){
                    errors.rejectValue("sellProduct","Ticket not available");
                    break;
                }
            }
        }


    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderForm.class.equals(aClass);
    }

}
