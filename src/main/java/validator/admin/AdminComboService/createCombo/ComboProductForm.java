package validator.admin.AdminComboService.createCombo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComboProductForm {

    public ComboProduct comboProduct;
    private String productIds;

    private List<ComboProduct> comboProducts;

    public ComboProduct getComboProduct() {
        return comboProduct;
    }

    public void setComboProduct(ComboProduct comboProduct) {
        this.comboProduct = comboProduct;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public List<ComboProduct> getComboProducts() {
        return comboProducts;
    }

    public void setComboProducts(List<ComboProduct> comboProducts) {
        this.comboProducts = comboProducts;
    }

    @Override
    public String toString() {
        return "ComboProductForm{" +
                "comboProduct=" + comboProduct +
                ", productIds='" + productIds + '\'' +
                ", comboProducts=" + comboProducts +
                '}';
    }
}
