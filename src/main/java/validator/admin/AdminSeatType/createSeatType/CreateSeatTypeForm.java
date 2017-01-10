package validator.admin.AdminSeatType.createSeatType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.MapsId;
import javax.validation.constraints.*;

/**
 * Created by sunno on 1/6/17.
 */
public class CreateSeatTypeForm {
    @NotBlank(message = "Name required")
    @Length(max=50,message = "Name too large")
    private String name;

    @NotNull(message = "Adult Price is required")
    @NumberFormat(style= NumberFormat.Style.CURRENCY)
    private Double adultPrice;

    @NotNull(message = "Child Price is required")
    private Double childPrice;

    private boolean isDefault;

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public Double getAdultPrice() {
        return adultPrice;
    }

    public Double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Double childPrice) {
        this.childPrice = childPrice;
    }

    public void setAdultPrice(Double adultPrice) {
        this.adultPrice = adultPrice;
    }

    @Override
    public String toString() {
        return "CreateSeatTypeFrom{" +
                "name='" + name + '\'' +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice+
                ", isDefault=" + isDefault+
                '}';
    }
}
