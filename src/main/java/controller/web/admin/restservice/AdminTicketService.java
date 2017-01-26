package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.SeatTypeDao;
import dao.TicketDao;
import dao.VatSettingDao;
import entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminTicketService.createTicket.CreateTicketForm;
import validator.admin.AdminTicketService.createTicket.CreateTicketValidator;

import javax.validation.Valid;

/**
 * Created by sunno on 1/25/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/ticket")
public class AdminTicketService {
    @Autowired
    SeatTypeDao seatTypeDao;

    @Autowired
    VatSettingDao vatSettingDao;

    @Autowired
    TicketDao ticketDao;

    @Autowired
    CreateTicketValidator createTicketValidator;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createTicket(@Valid CreateTicketForm createTicketForm, BindingResult result){

        System.out.println(createTicketForm);

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
        createTicketValidator.validate(createTicketForm,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation  [End] *************/



        /***************** Service  [Start] *************/
        Ticket ticket = new Ticket();

        ticket.setName(createTicketForm.getName());
        ticket.setDescription(createTicketForm.getDescription());
        ticket.setAnnotation(createTicketForm.getAnnotation());
        ticket.setPrintedPrice(createTicketForm.getPrintedPrice());
        ticket.setStartDate(createTicketForm.getFormattedStartDate());
        ticket.setEndDate(createTicketForm.getFormattedEndDate());
        ticket.setIsAdult(createTicketForm.getIsAdult());
        ticket.setIsChild(createTicketForm.getIsChild());
        ticket.setSeatType(seatTypeDao.getById(createTicketForm.getSeatTypeId()));
        ticket.setVat(vatSettingDao.getById(createTicketForm.getVatId()));
        ticket.setStatus(true);
        ticket.setCreatedBy(1);
        /***************** Service  [Ends] *************/

        ticket = ticketDao.insert(ticket);


        return ResponseEntity.status(HttpStatus.OK).body(ticket);

    }




}
