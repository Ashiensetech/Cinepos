package validator.admin.AdminComboService.createCombo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sarwar on 2/16/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComboProduct {

    private int productId;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComboProduct that = (ComboProduct) o;

        if (productId != that.productId) return false;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "ComboProduct{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
