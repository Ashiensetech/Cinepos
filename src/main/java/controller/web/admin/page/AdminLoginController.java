package controller.web.admin.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping(value = "/admin-login")
public class AdminLoginController {
    @RequestMapping(value = "/auth")
    public ModelAndView index(){
     return new ModelAndView("web-admin/login/login");
    }


}
