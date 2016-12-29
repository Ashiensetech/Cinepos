package controller;

/**
 * Created by mi on 12/29/16.
 */

import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.MediaType;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.ResponseBody;
        import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomRequestExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ModelAndView handle404(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String uri = request.getRequestURI();
        System.out.println(uri);

        if(uri.contains("/api/")){
            return new ModelAndView("error/api/404");
        }
        return new ModelAndView("error/web/404");
    }

}
