package validator.admin.AdminSellsService.CreateSells;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sarwar on 2/3/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartForm {

     private Integer Id;
     private int quantity;
     private float price;
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
        if (Id != null ? !Id.equals(cartForm.Id) : cartForm.Id != null) return false;
        return sellingType != null ? sellingType.equals(cartForm.sellingType) : cartForm.sellingType == null;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + quantity;
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (sellingType != null ? sellingType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CartForm{" +
                "Id=" + Id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", sellingType='" + sellingType + '\'' +
                '}';
    }
}
