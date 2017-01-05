package validator.admin.AdminUserService.createUser;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.ApplicationContext;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by mi on 1/3/17.
 */
public class CreateAdminUserForm {

    @Length(max = 50,message = "First name is too large")
    @NotBlank(message = "First name required")
    private String firstName;
    @Length(max = 50,message = "Last name is too large")
    @NotBlank(message = "Last name required")
    private String  lastName;

    @Length(max = 30,message = "Email is too large")
    @NotBlank(message = "Email required")
    @Email(message = "Email is not valid")
    private String  email;

    @NotBlank(message = "Date of birth required")
    private String dob;

    @Length(max = 30,message = "Phone is too large")
    @NotBlank(message = "Phone required")
    private String phone;

    @NotBlank(message = "Address required")
    private String address;

    @NotBlank(message = "Gender required")
    private String gender;

    @Length(max = 30,message = "User is too large")
    @NotBlank(message = "User name required")
    private String  userName;

    @NotBlank(message = "Password name required")
    @Length(min = 6,message = "At least 6 character")
    private String  password;

    @NotNull
    private Integer roleId;

    private java.sql.Date  dobDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getDobDate() {
        return dobDate;
    }

    public void setDobDate(Date dobDate) {
        this.dobDate = dobDate;
    }

    @Override
    public String toString() {
        return "CreateAdminUserForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
