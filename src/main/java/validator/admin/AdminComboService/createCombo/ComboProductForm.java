package validator.admin.AdminComboService.createCombo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComboProductForm {

    public ComboProductDetailsForm comboProductDetailsForm;
    private String productIds;

    private List<ComboProductDetailsForm> comboProductDetailsForms;

    public ComboProductDetailsForm getComboProductDetailsForm() {
        return comboProductDetailsForm;
    }

    public void setComboProductDetailsForm(ComboProductDetailsForm comboProductDetailsForm) {
        this.comboProductDetailsForm = comboProductDetailsForm;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public List<ComboProductDetailsForm> getComboProductDetailsForms() {
        return comboProductDetailsForms;
    }

    public void setComboProductDetailsForms(List<ComboProductDetailsForm> comboProductDetailsForms) {
        this.comboProductDetailsForms = comboProductDetailsForms;
    }

    @Override
    public String toString() {
        return "ComboProductForm{" +
                "comboProduct=" + comboProductDetailsForm +
                ", productIds='" + productIds + '\'' +
                ", comboProducts=" + comboProductDetailsForms +
                '}';
    }
}
