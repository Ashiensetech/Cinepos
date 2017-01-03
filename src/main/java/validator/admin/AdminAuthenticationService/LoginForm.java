package validator.admin.AdminAuthenticationService;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mi on 1/3/17.
 */
public class LoginForm {

    @NotBlank
    @Email(message = "Not a valid email")
    private String email;

    @NotBlank(message = "Password can not be empty")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
