package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import entity.Circuit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sarwar on 1/13/2017.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/concession-product")
public class AdminConcessionProductController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("web-admin/circuit/index-circuit");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createDistributor(){
        ModelAndView modelAndView= new ModelAndView("web-admin/concession-product/create-concession-product");
        return modelAndView;
    }
}
