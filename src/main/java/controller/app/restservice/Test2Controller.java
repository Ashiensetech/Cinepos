package controller.app.restservice;

import authuser.CustomUserDetails;
import com.fasterxml.jackson.annotation.JsonView;
import dao.CategoryDao;
import entity.Category;
import entity.iface.AppCategory;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import utility.VelocityUtil;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Created by mi on 12/21/16.
 */
@RestController
@RequestMapping("/api/admin/category")
public class Test2Controller {
    @Autowired
    CategoryDao categoryDao;
    @RequestMapping(value="/all")
    public ResponseEntity<List<Category>> getAppCategory(Authentication authentication){
        VelocityContext context = new VelocityContext();
        /*Authentication a = SecurityContextHolder.getContext().getAuthentication();*/
        CustomUserDetails currentUser = (CustomUserDetails)authentication.getPrincipal();
        System.out.println(currentUser);
        return new ResponseEntity<List<Category>>(categoryDao.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}")
    @JsonView(AppCategory.class)
    public ResponseEntity<Category> getAppCategoryList(@PathVariable("id") Integer id){
        Category category = categoryDao.getById(id);

        HttpStatus httpStatus = (category==null)?HttpStatus.NO_CONTENT: HttpStatus.OK;

        return ResponseEntity.status(httpStatus).body(category);
    }


    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
    public String handleIOException(NoSuchRequestHandlingMethodException ex, HttpServletRequest request) {
        System.out.println("HI");
        return ClassUtils.getShortName(ex.getClass());
    }


    @RequestMapping(value="/mail-template-parse")
    @JsonView(AppCategory.class)
    public void mailTemplateParse(){

        VelocityContext context = new VelocityContext();
        context.put("lastName", "Mavis");
        context.put("firstName", "Roger");
        context.put("email", "mrRogers@wmail.com");
        context.put("title", "Mr.");
        System.out.println(VelocityUtil.getHtmlByTemplateAndContext("/template/email/test.vm", context));

    }



}
