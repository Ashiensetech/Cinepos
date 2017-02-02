package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.*;
import entity.*;
import entity.jspView.TicketSeat;
import helper.ScreenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.Schedule;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by sunno on 1/25/17.
 */

@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/ticket")
public class AdminTicketController {

    @Autowired
    SeatTypeDao seatTypeDao;

    @Autowired
    VatSettingDao vatSettingDao;

    @Autowired
    TicketDao ticketDao;

    @Autowired
    FilmScheduleDao filmScheduleDao;

    @Autowired
    FilmTimeDao filmTimeDao;

    @Autowired
    ScreenDao screenDao;

    @Autowired
    ScreenSeatDao screenSeatDao;

    @RequestMapping(value = "/create")
    public ModelAndView createTicket(){
        List<Screen> screens = screenDao.getAllActivated();

        ModelAndView mav =  new ModelAndView("web-admin/ticket/create-ticket");

        mav.addObject("screens", screens);
        return mav;
    }

    @RequestMapping(value = "/all")
    public ModelAndView allTicket(){
        List<Ticket> tickets = ticketDao.getAll();
        ModelAndView mav =  new ModelAndView("web-admin/ticket/all-tickets");
        mav.addObject("tickets",tickets);
        return mav;
    }

    @RequestMapping(value = "/edit/{ticketId}")
    public ModelAndView editTicket(Authentication authentication, @PathVariable Integer ticketId){
        Ticket ticket = ticketDao.getById(ticketId);
        List<SeatType> seatTypes = seatTypeDao.getAll();
        List<VatSetting> vats = vatSettingDao.getAll();
        ModelAndView mav =  new ModelAndView("web-admin/ticket/edit-ticket");
        mav.addObject("seatTypes",seatTypes);
        mav.addObject("vats",vats);
        mav.addObject("ticket",ticket);
        return mav;
    }
    @RequestMapping(value = "/partial/ticket-create/{filmTimeId}/{seatId}")
    public ModelAndView partialGetCreateTicketForm(@PathVariable Integer filmTimeId,
                                                   @PathVariable Integer seatId){
        List<SeatType> seatTypes = seatTypeDao.getAll();
        List<Screen> screens = screenDao.getAllActivated();
        VatSetting vat = vatSettingDao.getFirst();
        FilmTime filmTime = filmTimeDao.getById(filmTimeId);
        Ticket ticket = ticketDao.getByFilmTimeAndSeatId(filmTimeId, seatId);
        if(vat==null){
            vat = new VatSetting();
            vat.setAmount(0f);
        }
        if(ticket==null){
            ticket = new Ticket();
        }
        if(filmTime==null){
            // Redirect
        }
        ModelAndView mav =  new ModelAndView("web-admin/ticket/partial-create-ticket-form");

        mav.addObject("ticket",ticket);
        mav.addObject("seatId",seatId);
        mav.addObject("filmTime",filmTime);

        mav.addObject("screens", screens);
        mav.addObject("seatTypes",seatTypes);
        mav.addObject("vat",vat);
        return mav;
    }
    @RequestMapping(value = "/seat/partial/{filmTimeId}")
    public ModelAndView partialGetScreenSeat(@PathVariable Integer filmTimeId){
        FilmTime filmTime = filmTimeDao.getById(filmTimeId);
        FilmSchedule filmSchedule = filmScheduleDao.getById(filmTime.getFilmScheduleId());
        Screen screen = filmSchedule.getScreen();


        if(screen.getSeats() == null || screen.getSeats().size()==0){
            // redirect
        }

        List< List<ScreenSeat>> screenSeatList = ScreenHelper.singleDimensionToTwoDimensionList(screen.getSeats(), screen.getRowCount(), screen.getRowCount());
        List<Ticket> tickets = ticketDao.getByFilmTimeId(filmTimeId);
        System.out.println(tickets);
        List<TicketSeat> ticketSeats = new ArrayList<>();
        for(List<ScreenSeat> seatRow : screenSeatList){
            for(ScreenSeat seat : seatRow){
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
        }



        List< List<TicketSeat>> ticketSeatList =  ScreenHelper.singleDimensionToTwoDimensionListForTicketSeat(ticketSeats, screen.getRowCount(), screen.getRowCount());

        ModelAndView mav =  new ModelAndView("web-admin/ticket/partial-screen-seats");

        mav.addObject("filmTimeId",filmTimeId);
        mav.addObject("ticketSeatList",ticketSeatList);
        return mav;
    }

}
