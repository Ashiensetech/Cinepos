package validator.admin.AdminComboService.createCombo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sarwar on 2/16/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComboProductDetailsForm {

    private Integer productId;
    private Integer quantity;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComboProductDetailsForm that = (ComboProductDetailsForm) o;

        if (productId != that.productId) return false;
        if (quantity != that.quantity) return false;
        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + quantity;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }


}
