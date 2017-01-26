package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.SeatTypeDao;
import dao.TicketDao;
import dao.VatSettingDao;
import entity.SeatType;
import entity.Ticket;
import entity.VatSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/create")
    public ModelAndView createTicket(){
        List<SeatType> seatTypes = seatTypeDao.getAll();
        List<VatSetting> vats = vatSettingDao.getAll();
        ModelAndView mav =  new ModelAndView("web-admin/ticket/create-ticket");
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
}
