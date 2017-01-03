package controller.web.admin.restservice;

import authuser.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import utility.ServiceResponse;
import validator.admin.AdminAuthenticationService.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by mi on 1/2/17.
 */

@RestController
@RequestMapping("/auth")
public class AdminAuthenticationService {

    @Autowired
    @Qualifier("authenticationManager")
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/admin/do-auth", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid LoginForm loginForm, BindingResult result,HttpServletRequest request) {
        String errorMsg = "Email or password is wrong";
        try {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);
            if(serviceResponse.hasErrors()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(serviceResponse.getFormError());
            }

            String username=loginForm.getEmail();
            String password=loginForm.getPassword();

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            System.out.println("token "+token);
            token.setDetails(new WebAuthenticationDetails(request));

            Authentication auth = authenticationManager.authenticate(token);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);



            if(auth.isAuthenticated()){
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

                return ResponseEntity.status(HttpStatus.OK).body((CustomUserDetails)auth.getPrincipal());
            }
        } catch (Exception e) {

        }
        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));
    }
    @RequestMapping(value = "/admin/do-logout", method = RequestMethod.POST)
    public CustomUserDetails logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", null);
        return new CustomUserDetails();
    }


}
