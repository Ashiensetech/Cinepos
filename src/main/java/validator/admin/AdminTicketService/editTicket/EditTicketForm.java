package validator.admin.AdminTicketService.editTicket;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by sunno on 1/27/17.
 */
public class EditTicketForm {


    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Id is required")
    private Integer id;

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

    private Boolean isChild;

    private Boolean isAdult;

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getIsChild() {
        return isChild;
    }

    public void setisChild(Boolean child) {
        isChild = child;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean adult) {
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
        return "EditTicketForm{" +
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
