package controller.web.admin.page;


import controller.web.admin.AdminUriPreFix;
import dao.AuthCredentialDao;
import dao.UserRoleDao;
import entity.AuthCredential;
import entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    AuthCredentialDao authCredentialDao;
    @RequestMapping("/all")
    public ModelAndView allAdminUserPage(Authentication authentication){
        ModelAndView mav = new ModelAndView("web-admin/admin-users/all-user");
        List<AuthCredential> adminUsers = authCredentialDao.getAllAdminUser();
        mav.addObject("adminUsers",adminUsers);
        return mav;
    }

    @RequestMapping("/create")
    public ModelAndView createAdminUserPage(Authentication authentication){
        ModelAndView mav = new ModelAndView("web-admin/admin-users/create-user");
        List<UserRole> userRoles = userRoleDao.getAllAdminstrative();
        mav.addObject("userRoles",userRoles);
        return mav;
    }
    @RequestMapping("/edit/{authCredentialId}")
    public ModelAndView editAdminUserPage(Authentication authentication,@PathVariable Integer authCredentialId){

        AuthCredential adminUser = authCredentialDao.getById(authCredentialId);
        if(adminUser==null){
            return new ModelAndView("web-admin/admin-users/all-user");
        }
        ModelAndView mav = new ModelAndView("web-admin/admin-users/edit-user");

        List<UserRole> userRoles = userRoleDao.getAllAdminstrative();
        mav.addObject("userRoles",userRoles);
        mav.addObject("adminUser",adminUser);
        return mav;
    }

}
