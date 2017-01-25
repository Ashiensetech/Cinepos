package validator.admin.AdminTicketService.createTicket;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by sunno on 1/25/17.
 */
public class CreateTicketForm {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Annotation is required")
    private String annotation;

    @NotNull(message = "Printed price is required")
    private Double printedPrice;

    @NotNull(message = "Seat type is required")
    private Integer seatTypeId;

    @NotNull(message = "Vat is required")
    private Integer vatId;

    @NotNull(message = "Start Date is required")
    private String startDate;

    @NotNull(message = "End Date is required")
    private String endDate;

    private Date formattedStartDate;

    private Date formattedEndDate;

    @NotNull(message = "Is child is required")
    private Boolean isChild;

    @NotNull(message = "Is adult is required")
    private Boolean isAdult;

    @NotNull(message = "Status is required")
    private Boolean status;

    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
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

    public Double getPrintedPrice() {
        return printedPrice;
    }

    public void setPrintedPrice(Double printedPrice) {
        this.printedPrice = printedPrice;
    }

    public Integer getVatId() {
        return vatId;
    }

    public void setVatId(Integer vatId) {
        this.vatId = vatId;
    }

    public Boolean getChild() {
        return isChild;
    }

    public void setChild(Boolean child) {
        isChild = child;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CreateTicketForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", annotation='" + annotation + '\'' +
                ", printedPrice=" + printedPrice +
                ", seatTypeId=" + seatTypeId +
                ", vatId=" + vatId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", formattedStartDate=" + formattedStartDate +
                ", formattedEndDate=" + formattedEndDate +
                ", isChild=" + isChild +
                ", isAdult=" + isAdult +
                ", status=" + status +
                '}';
    }
}
