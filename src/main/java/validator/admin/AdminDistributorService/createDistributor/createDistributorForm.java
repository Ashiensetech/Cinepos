package validator.admin.AdminDistributorService.createDistributor;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class createDistributorForm {

    @NotBlank(message = "Distributor name are required")
    @Length(max = 30,message = "Distributor name too large")
     private String name;

    @NotBlank(message = "Primary email are required")
    @Email(message = "Primary email is not valid")
    @Length(max = 35,message = "Primary email is too large")

    private String primaryEmail;

    @NotBlank(message = "Secondary email are required")
    @Email(message = "Secondary email is not valid")
    @Length(max = 35,message = "Secondary email is too large")
    private String secondaryEmail;

    @NotBlank(message = "Phone are required")
    @Length(max = 30,message = "Phone is too large")
    private String phone;

    @NotBlank(message = "Address are required")
    private String address;

    @NotBlank(message = "Description are required")
    @Size.List ({
            @Size(min=50, message="Description must be at least {min} characters"),
            @Size(max=250, message="Description must be less than {max} characters")
    })
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DistributorForm{" +
                "name='" + name + '\'' +
                ", primaryEmail='" + primaryEmail + '\'' +
                ", secondaryEmail='" + secondaryEmail + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
