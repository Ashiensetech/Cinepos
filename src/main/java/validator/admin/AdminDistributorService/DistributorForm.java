package validator.admin.AdminDistributorService;


import org.hibernate.validator.constraints.NotBlank;

public class DistributorForm {
    @NotBlank(message = "Distributor name are required")
   private String name;

    @NotBlank(message = "Primary email are required")
   private String primaryEmail;

    @NotBlank(message = "Secondary email are required")
   private String secondaryEmail;

    @NotBlank(message = "Phone are required")
   private String phone;

    @NotBlank(message = "Address are required")
   private String address;

    @NotBlank(message = "Description are required")
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
