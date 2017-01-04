package authuser;

import entity.AuthCredential;
import entity.UserInf;
import entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by mi on 12/27/16.
 */
public class CustomUserDetails extends AuthCredential implements UserDetails {
    public CustomUserDetails(AuthCredential authCredential) {
        super(authCredential.getId(),
                authCredential.getIsAdmin(),
                authCredential.getUserInf(),
                authCredential.getUserRole(),
                authCredential.getUserName(),
                authCredential.getPassword(),
                authCredential.getIsActivated(),
                authCredential.getIsEmailConfirmed(),
                authCredential.getChangedDefaultPassword(),
                authCredential.getCreatedBy(),
                authCredential.getCreatedAt());

    }

    @Override
    public String getUsername() {
        return this.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_"+this.getUserRole().getRoleName());
    }

    @Override
    public String toString() {
        return "CustomUserDetails : " + super.toString();
    }
}
