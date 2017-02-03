package validator.admin.AdminSellsService.CreateSells;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sarwar on 2/3/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartForm {

     private int Id;
     private int quantity;
     private float price;
     private String sellingType;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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
    public String toString() {
        return "CartForm{" +
                "Id=" + Id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", sellingType='" + sellingType + '\'' +
                '}';
    }
}
