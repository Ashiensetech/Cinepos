package controller.web.admin.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "terminal")
public class AdminTerminalController {

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public  ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("web-admin/terminal/index");
        return modelAndView;
    }
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView modelAndView=new ModelAndView("web-admin/terminal/create");
        return modelAndView;
    }


}
