package validator.admin.restservice.ticket.create;

import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sunno on 1/25/17.
 */
@Service
public class CreateTicketValidator implements Validator {

    @Autowired
    FilmTimeDao filmTimeDao;

    @Autowired
    ScreenSeatDao screenSeatDao;

    @Autowired
    VatSettingDao vatSettingDao;

    @Autowired
    TicketDao ticketDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateTicketForm createTicketForm = (CreateTicketForm) obj;

      /*  try {
            createTicketForm.setFormattedStartDate(DateHelper.getStringToDate(createTicketForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            createTicketForm.setFormattedEndDate(DateHelper.getStringToDate(createTicketForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }*/

       /* if (createTicketForm.getFormattedEndDate().before(createTicketForm.getFormattedStartDate())) {
            errors.rejectValue("endDate", "End Date should be after start date");
        }*/




        VatSetting vatSetting= vatSettingDao.getById(createTicketForm.getVatId());
        if(vatSetting==null){
            errors.rejectValue("vatId", "Vat does not exists");
        }

       /* Ticket ticket= ticketDao.getBySeatAndDates(createTicketForm.getSeatTypeId(),createTicketForm.getFormattedStartDate(),createTicketForm.getFormattedEndDate());
        if(ticket!=null){
            errors.rejectValue("startDate", "Start date or end date already exist");
        }*/


        FilmTime filmTime = filmTimeDao.getById(createTicketForm.getFilmTimeId());
        if(filmTime==null){
            errors.rejectValue("filmTimeId", "Film time does not exist");
        }

        ScreenSeat  screenSeat = screenSeatDao.getById(createTicketForm.getSeatId());
        if(screenSeat==null){
            errors.rejectValue("seatId", "Screen seat does not exist");
        }

        if(createTicketForm.getTicketId()>0){
            Ticket ticket = ticketDao.getById(createTicketForm.getTicketId());
            if(ticket==null){
                errors.rejectValue("ticketId", "Ticket not found");
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateTicketForm.class.equals(aClass);
    }
}
