package controller.web.admin.page;


import controller.web.admin.AdminUriPreFix;
import dao.UserRoleDao;
import entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mi on 1/3/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/user")
public class AdminUserController {
    @Autowired
    UserRoleDao userRoleDao;

    @RequestMapping("/create")
    public ModelAndView createAdminUserPage(Authentication authentication){
        ModelAndView mav = new ModelAndView("web-admin/admin-users/create-user");
        List<UserRole> userRoles = userRoleDao.getAllAdminstrative();
        mav.addObject("userRoles",userRoles);
        return mav;
    }
}
