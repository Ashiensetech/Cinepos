package controller.web.admin.restservice;

import authuser.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.web.admin.AdminUriPreFix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminAuthenticationService.LoginForm;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeForm;
import validator.admin.AdminFilmScheduleService.createOrMerge.CreateOrMergeValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * Created by mi on 1/2/17.
 */

@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/film-scheduling")
public class AdminFilmScheduleService {
    @Autowired
    CreateOrMergeValidator createOrMergeValidator;


    @RequestMapping(value = "/create-merge", method = RequestMethod.POST)
    public ResponseEntity<?> createOrMerge(@Valid CreateOrMergeForm createOrMergeForm, BindingResult result,HttpServletRequest request) {

        createOrMergeValidator.validate(createOrMergeForm,result);

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
