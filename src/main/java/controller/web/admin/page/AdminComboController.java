package controller.web.admin.page;

import com.sun.org.apache.xpath.internal.operations.Mod;
import controller.web.admin.AdminUriPreFix;
import dao.ComboDao;
import dao.ConcessionProductDao;
import dao.SeatTypeDao;
import dao.reportDao.ProductSaleReportViewDao;
import entity.Combo;
import entity.ConcessionProduct;
import entity.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Sarwar on 1/18/2017.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/combo")
public class AdminComboController {
    @Autowired
    ConcessionProductDao concessionProductDao;
    @Autowired
    ComboDao comboDao;
    @Autowired
    SeatTypeDao seatTypeDao;


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView index(){

        List<Combo> comboList=comboDao.getAll();
        ModelAndView modelAndView=new ModelAndView("web-admin/combo/all-combo");
        modelAndView.addObject("comboList",comboList);
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView create(){
        List<ConcessionProduct> concessionProductList= concessionProductDao.getAll();
        List<SeatType> seatTypeList=seatTypeDao.getAll();
        ModelAndView modelAndView=new ModelAndView("web-admin/combo/create-combo");
        modelAndView.addObject("concessionProductList",concessionProductList);
        modelAndView.addObject("seatTypeList",seatTypeList);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{comboId}",method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Integer comboId){
        Combo combos=comboDao.getById(comboId);
        List<ConcessionProduct> concessionProductList= concessionProductDao.getAll();
        ModelAndView modelAndView=new ModelAndView("web-admin/combo/edit-combo");
        modelAndView.addObject("concessionProductList",concessionProductList);
        modelAndView.addObject("combos",combos);
        System.out.println(combos);
        return modelAndView;
    }
}
