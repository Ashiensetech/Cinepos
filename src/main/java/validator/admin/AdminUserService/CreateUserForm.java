package validator.admin.AdminUserService;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created by mi on 1/3/17.
 */
public class CreateUserForm {

    @NotBlank(message = "First name required")
    private String firstName;

    @NotBlank(message = "Last name required")
    private String  lastName;

    @NotBlank(message = "Email required")
    @Email(message = "Email is not valid")
    private String  email;

    @NotBlank(message = "Phone required")
    private String phone;

    @NotBlank(message = "Address required")
    private String address;

    @NotBlank(message = "Gender required")
    private String gender;

    @NotBlank(message = "Status required")
    private String status;

    @NotBlank(message = "User name required")
    private String  userName;

    @NotBlank @Min(value = 6,message = "At least 6 character")
    private String  password;
    


}
