package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.SeatPriceShiftDao;
import dao.SeatTypeDao;
import entity.SeatPriceShift;
import entity.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sunno on 1/16/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/seat-price-shift")
public class AdminSeatPriceShiftController {
    @Autowired
    SeatPriceShiftDao seatPriceShiftDao;

    @Autowired
    SeatTypeDao seatTypeDao;

    @RequestMapping(value = "/create")
    public ModelAndView createSeatPriceShift(){
        List<SeatType> seatTypes = seatTypeDao.getAll();
        ModelAndView mav =  new ModelAndView("web-admin/seat-price-shift/create-seat-price-shift");
        mav.addObject("seatTypes",seatTypes);
        return mav;
    }


    @RequestMapping("/all")
    public ModelAndView allSeatTypePage(){
        ModelAndView mav = new ModelAndView("web-admin/seat-price-shift/all-seat-price-shifts");
        List<SeatPriceShift> seatPriceShifts= seatPriceShiftDao.getAll();
        mav.addObject("seatPriceShifts",seatPriceShifts);
        return mav;
    }


}
