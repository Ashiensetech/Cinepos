package validator.admin.AdminConcessionProductService.createConcessionProduct;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CreateConcessionProductForm {

    @NotBlank(message = "Product name are required")
    @Length(max = 60,message = "Product name too large")
    private String name;

    @NotBlank(message = "Product description are required")
    @Size.List ({
            @Size(min=50, message="Description must be at least {min} characters"),
            @Size(max=250, message="Description must be less than {max} characters")
    })
    private String description;

    @NotBlank(message = "Product annotation are required")
    @Length(max = 60,message = "Product annotation too large")
    private String annotation;

    @NotNull(message = "Product category are required")
    private Integer productCategory;

    @NotNull(message = "Product unit are required")
    private Integer unit;

    @NotNull(message = "Product selling price are required")
    private Float sellingPrice;

    @NotNull(message = "Product buying price are required")
    private Float buyingPrice;

    private Integer isPriceShift;
    private Integer isCombo;
    private Integer remotePrint;


    @NotNull(message = "Product Image is required")
    private Integer productImageToken;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public Integer getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Integer productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }


    public Integer getIsPriceShift() {
        return isPriceShift;
    }

    public void setIsPriceShift(Integer isPriceShift) {
        this.isPriceShift = isPriceShift;
    }

    public Integer getIsCombo() {
        return isCombo;
    }

    public void setIsCombo(Integer isCombo) {
        this.isCombo = isCombo;
    }

    public Integer getRemotePrint() {
        return remotePrint;
    }

    public void setRemotePrint(Integer remotePrint) {
        this.remotePrint = remotePrint;
    }

    public Integer getProductImageToken() {
        return productImageToken;
    }

    public void setProductImageToken(Integer productImageToken) {
        this.productImageToken = productImageToken;
    }

    @Override
    public String toString() {
        return "createConcessionProductForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", annotation='" + annotation + '\'' +
                ", productCategory=" + productCategory +
                ", unit=" + unit +
                ", sellingPrice=" + sellingPrice +
                ", buyingPrice=" + buyingPrice +
                ", isPriceShift=" + isPriceShift +
                ", isCombo=" + isCombo +
                ", remotePrint=" + remotePrint +
                ", productImageToken=" + productImageToken +
                '}';
    }
}
