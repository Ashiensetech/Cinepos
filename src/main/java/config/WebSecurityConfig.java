package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by mi on 12/27/16.
 */
@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  /*  @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,UserDetailsService customUserService) throws Exception{
        auth.userDetailsService(customUserService).passwordEncoder(new BCryptPasswordEncoder());
    }*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
         .authorizeRequests()
                .antMatchers("/resources/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
            .formLogin()
//                .loginPage("/loginPageUrl") // Custom Login page
//                .permitAll()
                .and()
            .httpBasic();
    }
    @Bean(name="authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
