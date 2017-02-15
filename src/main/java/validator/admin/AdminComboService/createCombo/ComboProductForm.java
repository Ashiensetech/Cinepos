package validator.admin.AdminComboService.createCombo;

/**
 * Created by Sarwar on 2/15/2017.
 */
public class ComboProductForm {
    private Integer productId;
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComboProductForm that = (ComboProductForm) o;

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        return quantity != null ? quantity.equals(that.quantity) : that.quantity == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComboProductForm{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
