package validator.admin.AdminAuthenticationService;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mi on 1/3/17.
 */
public class LoginForm {

    @NotBlank(message = "User name can not be empty")
    private String userName;

    @NotBlank(message = "Password can not be empty")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
