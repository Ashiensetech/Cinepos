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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import utility.ServiceResponse;
import validator.admin.AdminAuthenticationService.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

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
        String errorMsg = "Username or password is wrong";
        try {
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);
            if(serviceResponse.hasErrors()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMsg);
            }

            String username=loginForm.getUserName();
            String password=loginForm.getPassword();

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            System.out.println("token "+token);
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            if(auth.isAuthenticated()){
                HttpSession session = request.getSession(true);
                CustomUserDetails customUserDetails = (CustomUserDetails)auth.getPrincipal();

                if(!customUserDetails.getIsActivated()){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ServiceResponse.getMsg("Account is inactive"));
                }
                if(!customUserDetails.getUserRole().getRoleName().equals("ADMIN")){
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ServiceResponse.getMsg("You don't have privilege"));
                }
                session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

                return ResponseEntity.status(HttpStatus.OK).body((CustomUserDetails)auth.getPrincipal());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ServiceResponse.getMsg(errorMsg));
    }
    @RequestMapping(value = "/admin/do-logout", method = RequestMethod.GET)
    public ResponseEntity<Map<String,String>> logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", null);
        return ResponseEntity.ok(ServiceResponse.getMsg("Logout"));
    }


}
