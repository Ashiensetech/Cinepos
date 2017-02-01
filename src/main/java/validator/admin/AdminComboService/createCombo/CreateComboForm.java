package validator.admin.AdminComboService.createCombo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 1/19/2017.
 */
public class CreateComboForm {

    @NotBlank(message = "Combo Name are required")
    @Length(max = 55,message = "Site name too large")
    private String comboName;

    @NotBlank(message = "Combo Details are required")
    @Length(max = 255,message = "Combo Details too large")
    private String details;

    @NotNull(message = "Combo price are required")
    private Float price;

    @NotNull(message = "Start Time is required")
    private String startDate;

    @NotNull(message = "End Date is required")
    private String endDate;

    @NotNull(message = "Combo Type is required")
    private String comboType;

    @NotNull(message = "Product are  required")
    private String productIds;

    private int seatTypeId;

    private List<Integer> productsIdArray = new ArrayList<>();



    private Date formattedStartDate;

    private Date formattedEndDate;




    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getComboType() {
        return comboType;
    }

    public void setComboType(String comboType) {
        this.comboType = comboType;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }


    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public Date getFormattedStartDate() {
        return formattedStartDate;
    }

    public void setFormattedStartDate(Date formattedStartDate) {
        this.formattedStartDate = formattedStartDate;
    }

    public Date getFormattedEndDate() {
        return formattedEndDate;
    }

    public void setFormattedEndDate(Date formattedEndDate) {
        this.formattedEndDate = formattedEndDate;
    }


    public List<Integer> getProductsIdArray() {
        return productsIdArray;
    }

    public void setProductsIdArray(List<Integer> productsIdArray) {
        this.productsIdArray = productsIdArray;
    }

    @Override
    public String toString() {
        return "CreateComboForm{" +
                "comboName='" + comboName + '\'' +
                ", details='" + details + '\'' +
                ", price=" + price +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", comboType='" + comboType + '\'' +
                ", productIds='" + productIds + '\'' +
                ", seatTypeId=" + seatTypeId +
                ", productsIdArray=" + productsIdArray +
                ", formattedStartDate=" + formattedStartDate +
                ", formattedEndDate=" + formattedEndDate +
                '}';
    }
}
