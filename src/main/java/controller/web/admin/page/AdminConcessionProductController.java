package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionProductCategoryDao;
import entity.Circuit;
import entity.ConcessionProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Sarwar on 1/13/2017.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/concession-product")
public class AdminConcessionProductController {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("web-admin/concession-product/all-concession-product");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createDistributor(){
        List<ConcessionProductCategory> productCategoryList=concessionProductCategoryDao.getAll();
        ModelAndView modelAndView= new ModelAndView("web-admin/concession-product/create-concession-product");
        modelAndView.addObject("ProductCategoryList",productCategoryList);
        return modelAndView;
    }
}
