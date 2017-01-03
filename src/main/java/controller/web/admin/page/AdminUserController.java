package controller.web.admin.page;


import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mi on 1/3/17.
 */
public class AdminUserController {


    public ModelAndView createAdminUserPage(Authentication authentication){

        return new ModelAndView("web-admin/admin-users/create-user");
    }
}
