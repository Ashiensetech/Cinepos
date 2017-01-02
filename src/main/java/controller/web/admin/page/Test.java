package controller.web.admin.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mi on 1/2/17.
 */
@Controller
@RequestMapping("/admin")
public class Test {
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init(){
        return new ModelAndView("web-admin/test");
    }
}
