package validator.admin.restservice.combo.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Sarwar on 2/16/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComboProductDetailsForm {

    private Integer productId;
    private Integer seatTypeId;
    private Integer quantity;
    private String type;

    public Integer getProductId() {
        return productId;
    }

    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
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

        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (seatTypeId != null ? !seatTypeId.equals(that.seatTypeId) : that.seatTypeId != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        return !(type != null ? !type.equals(that.type) : that.type != null);

    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (seatTypeId != null ? seatTypeId.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
