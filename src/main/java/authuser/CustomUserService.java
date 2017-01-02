package authuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by mi on 12/27/16.
 */
public class CustomUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("at loadUserByUsername");
        if(!s.equals("user")) throw new UsernameNotFoundException("Not important");
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(10);
        customUserDetails.setEmail("user");
        customUserDetails.setPassword("$2a$08$UlxPkBEnN//UXi72l8pi5u33k/OmtDd9oWW5fsq8CRYg6C9zYba02");
        customUserDetails.setRole("ADMIN");
        System.out.println(customUserDetails);
        return customUserDetails;
    }
}
