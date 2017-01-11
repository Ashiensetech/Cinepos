package validator.admin.AdminVatSettingService.createVatSetting;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Sarwar on 1/11/2017.
 */
public class createVatSettingForm {

    private Integer Id;

    @NotBlank(message = "Name are required")
    private String name;

    @NotNull(message = "The refund deduction are required")
    @DecimalMax(value = "99999.99", message = "The refund deduction can not be more than 99999.999 ")
    @DecimalMin(value = "1.00", message = "The refund deduction can not be less than 1.00 digit ")
    private Float amount;

    @NotBlank(message = "Description are required")
    @Size.List ({
            @Size(min=50, message="Description must be at least {min} characters"),
            @Size(max=250, message="Description must be less than {max} characters")
    })
    private String description;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "createVatSettingForm{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
