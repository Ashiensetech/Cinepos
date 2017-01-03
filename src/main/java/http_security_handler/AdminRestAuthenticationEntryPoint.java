package http_security_handler;

/**
 * Created by mi on 1/3/17.
 */

import java.io.IOException;

        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        import org.springframework.security.core.AuthenticationException;
        import org.springframework.security.web.AuthenticationEntryPoint;
        import org.springframework.stereotype.Component;

/**
 * This entry point is called once the request missing the authentication but if
 * the request dosn't have the cookie then we send the unauthorized response.
 *
 * @author malalanayake
 *
 */
@Component
public class AdminRestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest arg0, HttpServletResponse arg1,
                         AuthenticationException arg2) throws IOException, ServletException {
        arg1.setContentType("application/json");
        arg1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        arg1.getWriter().println("{\"msg\":\"Please login first\"}");
    }

}