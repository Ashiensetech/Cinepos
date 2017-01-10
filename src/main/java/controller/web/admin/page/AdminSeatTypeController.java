package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.SeatTypeDao;
import entity.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sunno on 1/9/17.
 */

@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/seat-type")
public class AdminSeatTypeController {

    @Autowired
    SeatTypeDao seatTypeDao;

    @RequestMapping(value = "/create")
    public ModelAndView createSeatTypePage(){
        ModelAndView mav =  new ModelAndView("web-admin/seat-type/create-seat-type");
        return mav;
    }

    @RequestMapping("/all")
    public ModelAndView allSeatTypePage(){
        ModelAndView mav = new ModelAndView("web-admin/seat-type/all-seat-types");
        List<SeatType> seatTypes = seatTypeDao.getAll();
        mav.addObject("seatTypes",seatTypes);
        return mav;
    }

    @RequestMapping("/edit/{seatTypeId}")
    public ModelAndView editAdminUserPage(Authentication authentication, @PathVariable Integer seatTypeId){
        ModelAndView mav =  new ModelAndView("web-admin/seat-type/edit-seat-type");
        SeatType seatType = seatTypeDao.getById(seatTypeId);
        mav.addObject("seatType",seatType);
        return mav;
    }


}

