package controller.app.restservice;

import controller.app.AppUriPreFix;
import dao.FilmScheduleDao;
import dao.FilmTimeDao;
import dao.SeatTypeDao;
import dao.TicketDao;
import entity.*;
import entity.jspView.TicketSeat;
import helper.ScreenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utility.ServiceResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mi on 2/3/17.
 */
@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/ticket")

public class AppTicketService {

    @Autowired
    FilmTimeDao filmTimeDao;

    @Autowired
    FilmScheduleDao filmScheduleDao;

    @Autowired
    TicketDao ticketDao;

    @Autowired
    SeatTypeDao seatTypeDao;


    @RequestMapping(value = "/seats/get-by-film-time/{filmTimeId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTicketSeatByFilmTimeId(@PathVariable int filmTimeId){
        /**
         * Checking Film Time existence
         * */
        FilmTime filmTime = filmTimeDao.getById(filmTimeId);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Error return
         * */
        if(filmTime == null ){
            serviceResponse.setValidationError("filmTimeId","No film time found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        /**
         * Get schedule by film id to get screen
         * */
        FilmSchedule filmSchedule = filmScheduleDao.getById(filmTime.getFilmScheduleId());
        Screen screen = filmSchedule.getScreen();

        /**
         * Get screen seats
         * */
        List< ScreenSeat> screenSeatList = screen.getSeats();

        List<Ticket> tickets = ticketDao.getByFilmTimeId(filmTimeId);
        List<TicketSeat> ticketSeats = new ArrayList<>();

        /**
         * Error return
         * */
        if(screenSeatList==null || screenSeatList.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ServiceResponse.getMsg("Sceen have not created seats"));
        }
        /**
         * Merging seat with ticket
         * Each seat has ticket for each show time (Film Time)
         * Ticket could be not created for seats in that case ticketSeat.getCurrentState() will null or empty
         * */
        for(ScreenSeat seat : screenSeatList){
            TicketSeat ticket =new TicketSeat(seat);
            Optional<Ticket> optionalTicket = null;
            if(tickets!=null && tickets.size()>0){
                optionalTicket = tickets.stream().filter(t->t.getScreenSeat().getId()==seat.getId()).findFirst();
            }

            if(optionalTicket!=null && optionalTicket.isPresent()){
                String currentState = optionalTicket.get().getCurrentState();

                System.out.println("currentState "+currentState);
                ticket.setCurrentState(currentState);
            }
            ticketSeats.add(ticket);
        }

        /**
         * Return Ticket Seat JSON
         * */
        List< List<TicketSeat>> ticketSeatList =  ScreenHelper.singleDimensionToTwoDimensionListForTicketSeat(ticketSeats, screen.getRowCount(), screen.getColumnCount());
        if(ticketSeatList==null || ticketSeatList.size()==0){
            ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticketSeatList);
    }
    @RequestMapping(value = "/get-by-film-time/{filmTimeId}", method = RequestMethod.GET)
    public ResponseEntity<?> getTicketByFilmTimeId(@PathVariable int filmTimeId){
        /**
         * Checking Film Time existence
         * */
        FilmTime filmTime = filmTimeDao.getById(filmTimeId);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /**
         * Error return
         * */
        if(filmTime == null ){
            serviceResponse.setValidationError("filmTimeId","No film time found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /**
         * Get ticket
         * */
        List<Ticket> tickets = ticketDao.getByFilmTimeId(filmTimeId);


        /**
         * Error return
         * */
        if(tickets==null || tickets.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ServiceResponse.getMsg("No ticket created"));
        }

        /**
         * Return Ticket  JSON
         * */
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }
    @RequestMapping(value = "/get-by-film-time/{filmTimeId}/{seatId}")
    public ResponseEntity<?> getTicketByFilmTimeAndSeatId(@PathVariable Integer filmTimeId,
                                                   @PathVariable Integer seatId){

        Ticket ticket = ticketDao.getByFilmTimeAndSeatId(filmTimeId, seatId);
        if(ticket==null ){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }
}
