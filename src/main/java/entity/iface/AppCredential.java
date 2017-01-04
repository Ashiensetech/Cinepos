package entity.iface;

import com.fasterxml.jackson.annotation.JsonView;
import entity.UserInf;
import entity.UserRole;

import java.sql.Timestamp;

/**
 * Created by mi on 1/4/17.
 */
public interface AppCredential {
    @JsonView(AppCredential.class)
    public int getId();

    @JsonView(AppCredential.class)
    public boolean getIsAdmin();

    @JsonView(AppCredential.class)
    public UserInf getUserInf();

    @JsonView(AppCredential.class)
    public String getUserName();

    @JsonView(AppCredential.class)
    public UserRole getUserRole();

}
