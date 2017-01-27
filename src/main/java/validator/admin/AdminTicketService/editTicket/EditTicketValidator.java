package validator.admin.AdminTicketService.editTicket;

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
 * Created by sunno on 1/27/17.
 */
@Service
public class EditTicketValidator implements Validator {

    @Autowired
    SeatTypeDao seatTypeDao;

    @Autowired
    VatSettingDao vatSettingDao;

    @Autowired
    TicketDao ticketDao;

    @Override
    public void validate(Object obj, Errors errors) {
        EditTicketForm editTicketForm = (EditTicketForm) obj;

        System.out.print(editTicketForm);
        try {
            editTicketForm.setFormattedStartDate(DateHelper.getStringToDate(editTicketForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            editTicketForm.setFormattedEndDate(DateHelper.getStringToDate(editTicketForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }

        if (editTicketForm.getFormattedEndDate().before(editTicketForm.getFormattedStartDate())) {
            errors.rejectValue("endDate", "End Date should be after start date");
        }
        SeatType seatType= seatTypeDao.getById(editTicketForm.getSeatTypeId());
        if(seatType==null){
            errors.rejectValue("seatTypeId", "Seat type does not exists");
        }

        VatSetting vatSetting= vatSettingDao.getById(editTicketForm.getVatId());
        if(vatSetting==null){
            errors.rejectValue("vatId", "Vat does not exists");
        }

        Ticket ticket= ticketDao.getBySeatAndDates(editTicketForm.getSeatTypeId(),editTicketForm.getFormattedStartDate(),editTicketForm.getFormattedEndDate());
        if(ticket!=null){
            errors.rejectValue("startDate", "Start date or end date already exist");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return EditTicketForm.class.equals(aClass);
    }

}
