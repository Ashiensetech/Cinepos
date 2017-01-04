package controller.web.admin.page;


import controller.web.admin.AdminUriPreFix;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mi on 1/3/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/user")
public class AdminUserController {

    @RequestMapping("/create")
    public ModelAndView createAdminUserPage(Authentication authentication){

        return new ModelAndView("web-admin/admin-users/create-user");
    }
}
