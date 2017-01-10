package validator.admin.AdminSeatType.createSeatType;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by sunno on 1/6/17.
 */
public class CreateSeatTypeForm {
    @NotBlank(message = "Name required")
    @Length(max=50,message = "Name too large")
    private String name;

    @NotNull(message = "Adult Price is required")
    private double adultPrice;

    @NotNull(message = "Child Price is required")
    private double childPrice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }

    @Override
    public String toString() {
        return "CreateSeatTypeFrom{" +
                "name='" + name + '\'' +
                ", adultPrice=" + adultPrice +
                ", childPrice=" + childPrice+
                '}';
    }


}
