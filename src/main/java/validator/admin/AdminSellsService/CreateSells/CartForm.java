package validator.admin.AdminSellsService.CreateSells;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by Sarwar on 2/3/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartForm {

     @NotNull(message = "Id is required")
     private Integer Id;

     @NotNull(message = "Quantity is required")
     private int quantity;

     @NotNull(message = "Price is required")
     private float price;

     private int screenId;
     private int ticketId;

    @NotBlank(message = "Selling type is required")
    private String sellingType;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getSellingType() {
        return sellingType;
    }

    public void setSellingType(String sellingType) {
        this.sellingType = sellingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartForm cartForm = (CartForm) o;

        if (quantity != cartForm.quantity) return false;
        if (Float.compare(cartForm.price, price) != 0) return false;
        if (screenId != cartForm.screenId) return false;
        if (ticketId != cartForm.ticketId) return false;
        if (Id != null ? !Id.equals(cartForm.Id) : cartForm.Id != null) return false;
        return sellingType != null ? sellingType.equals(cartForm.sellingType) : cartForm.sellingType == null;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + screenId;
        result = 31 * result + ticketId;
        result = 31 * result + (sellingType != null ? sellingType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CartForm{" +
                "Id=" + Id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", screenId=" + screenId +
                ", ticketId=" + ticketId +
                ", sellingType='" + sellingType + '\'' +
                '}';
    }
}
