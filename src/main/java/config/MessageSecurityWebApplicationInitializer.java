package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by mi on 12/27/16.
 */
@Order(2)
public class MessageSecurityWebApplicationInitializer {//} extends AbstractSecurityWebApplicationInitializer {
}