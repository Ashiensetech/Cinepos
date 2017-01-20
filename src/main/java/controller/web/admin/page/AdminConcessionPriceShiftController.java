package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionPriceShiftDao;
import dao.ConcessionProductDao;
import entity.ConcessionPriceShift;
import entity.ConcessionProduct;
import entity.Distributor;
import entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sunno on 1/16/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/concession-price-shift")
public class AdminConcessionPriceShiftController {
    @Autowired
    ConcessionPriceShiftDao concessionPriceShiftDao;

    @Autowired
    ConcessionProductDao concessionProductDao;

    @RequestMapping(value = "/create")
    public ModelAndView createConcessionPriceShift(){
        List<ConcessionProduct> concessionProducts = concessionProductDao.getAll();
        ModelAndView mav =  new ModelAndView("web-admin/concession-price-shift/create-concession-price-shift");
        mav.addObject("concessionProducts",concessionProducts);
        return mav;
    }

    @RequestMapping("/all")
    public ModelAndView allSeatTypePage(){
        ModelAndView mav = new ModelAndView("web-admin/concession-price-shift/all-concession-price-shifts");
        List<ConcessionPriceShift> concessionPriceShifts = concessionPriceShiftDao.getAll();
        mav.addObject("concessionPriceShifts",concessionPriceShifts);
        return mav;
    }

    @RequestMapping("/edit/{concessionProductPriceShiftId}")
    public ModelAndView editSeatPriceShiftPage(Authentication authentication, @PathVariable Integer concessionProductPriceShiftId){
        ModelAndView mav =  new ModelAndView("web-admin/concession-price-shift/edit-concession-price-shift");
        ConcessionPriceShift concessionPriceShift = concessionPriceShiftDao.getById(concessionProductPriceShiftId);
        List<ConcessionProduct> concessionProducts= concessionProductDao.getAll();
        mav.addObject("concessionProducts",concessionProducts);
        mav.addObject("concessionPriceShift",concessionPriceShift);
        return mav;
    }

}
