package authuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by mi on 12/27/16.
 */
public class CustomUserDetailsService implements UserDetailsService{
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
