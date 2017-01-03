package controller.web.admin.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Sarwar on 1/3/2017.
 */
@Controller
@RequestMapping(value = "distributor")
public class AdminDistributorController {
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView index(){
     ModelAndView modelAndView=new ModelAndView("web-admin/distributors/index");
     return modelAndView;
    }
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView modelAndView= new ModelAndView("web-admin/distributors/create");
        return modelAndView;
    }

}
