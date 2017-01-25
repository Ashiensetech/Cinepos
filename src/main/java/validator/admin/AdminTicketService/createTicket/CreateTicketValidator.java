package validator.admin.AdminTicketService.createTicket;

import dao.SeatTypeDao;
import dao.TicketDao;
import dao.VatSettingDao;
import entity.SeatType;
import entity.Ticket;
import entity.VatSetting;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;

/**
 * Created by sunno on 1/25/17.
 */
@Service
public class CreateTicketValidator implements Validator {

    @Autowired
    SeatTypeDao seatTypeDao;

    @Autowired
    VatSettingDao vatSettingDao;

    @Autowired
    TicketDao ticketDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateTicketForm createTicketForm = (CreateTicketForm) obj;

        try {
            createTicketForm.setFormattedStartDate(DateHelper.getStringToDate(createTicketForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            createTicketForm.setFormattedEndDate(DateHelper.getStringToDate(createTicketForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }

        if (createTicketForm.getFormattedEndDate().before(createTicketForm.getFormattedStartDate())) {
            errors.rejectValue("endDate", "End Date should be after start date");
        }
        Ticket ticket= ticketDao.getBySeatAndDates(createTicketForm.getSeatTypeId(),createTicketForm.getFormattedStartDate(),createTicketForm.getFormattedEndDate());
        if(ticket!=null){
            errors.rejectValue("startDate", "Start date or end date already exist");
        }

        SeatType seatType= seatTypeDao.getById(createTicketForm.getSeatTypeId());
        if(seatType==null){
            errors.rejectValue("seatTypeId", "Seat type does not exists");
        }

        VatSetting vatSetting= vatSettingDao.getById(createTicketForm.getVatId());
        if(vatSetting==null){
            errors.rejectValue("vatId", "Vat does not exists");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateTicketForm.class.equals(aClass);
    }
}
