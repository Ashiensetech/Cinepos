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
}
