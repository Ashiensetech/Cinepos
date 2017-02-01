package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.*;
import entity.*;
import helper.ScreenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.Schedule;
import java.util.List;

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

    @RequestMapping(value = "/create/{filmTimeId}")
    public ModelAndView createTicket(@PathVariable Integer filmTimeId){
        List<SeatType> seatTypes = seatTypeDao.getAll();
        List<Screen> screens = screenDao.getAllActivated();
        List<VatSetting> vats = vatSettingDao.getAll();
        FilmTime filmTime = filmTimeDao.getById(filmTimeId);

        if(filmTime == null){
            // redirect
        }
        FilmSchedule filmSchedule = filmScheduleDao.getById(filmTime.getFilmScheduleId());
        Screen screen = filmSchedule.getScreen();

        if(screen == null){
            // redirect
        }

        List<ScreenSeat> screenSeats = screenSeatDao.getByScreenId(screen.getId());
        if(screenSeats == null){
            // redirect
        }

        List< List<ScreenSeat>> screenSeatList = ScreenHelper.singleDimensionToTwoDimensionList(screenSeats, screen.getRowCount(), screen.getRowCount());
        ModelAndView mav =  new ModelAndView("web-admin/ticket/create-ticket");

        mav.addObject("screens", screens);
        mav.addObject("screenSeatList",screenSeatList);
        mav.addObject("seatTypes",seatTypes);
        mav.addObject("vats",vats);
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

}
