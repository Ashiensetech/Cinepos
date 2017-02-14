package validator.admin.AdminConcessionPriceShiftService.createConcessionPriceShift;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by sunno on 1/16/17.
 */
public class CreateConcessionPriceShiftForm {

    public Integer getConcessionProductId() {
        return concessionProductId;
    }

    public void setConcessionProductId(Integer concessionProductId) {
        this.concessionProductId = concessionProductId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @NotNull(message = "Product is required")
    private Integer concessionProductId;

    @NotNull(message = "Start Date is required")
    private String startDate;

    @NotNull(message = "End Date is required")
    private String endDate;

    private Date formattedStartDate;

    private Date formattedEndDate;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "The Price must be positive")
    private Double price;

//    @NotNull(message = "Status is required")
    private boolean status;

    @Override
    public String toString() {
        return "CreateFilmForm{" +
                "concessionProductId='" + concessionProductId +
                ", price=" + price +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate='" + endDate +
                ", formattedStartDate=" + formattedStartDate +
                ", formattedEndDate=" + formattedEndDate +
                '}';
    }

}
