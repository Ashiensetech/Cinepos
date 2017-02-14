package authuser;

import dao.AuthCredentialDao;
import entity.AuthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by mi on 12/27/16.
 */
@Component
public class CustomUserService implements UserDetailsService {
    @Autowired
    AuthCredentialDao authCredentialDao;
    @PostConstruct
    public void init(){
        t_authCredentialDao = authCredentialDao;
    }

    static AuthCredentialDao t_authCredentialDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthCredential authCredential = t_authCredentialDao.getByUserName(s);
        System.out.println(authCredential);
        if(authCredential==null) throw new UsernameNotFoundException("Not important");

        return new CustomUserDetails(authCredential);
    }
}
