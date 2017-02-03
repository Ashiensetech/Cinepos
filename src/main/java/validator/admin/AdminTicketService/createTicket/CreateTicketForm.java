package validator.admin.AdminTicketService.createTicket;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by sunno on 1/25/17.
 */
public class CreateTicketForm {

    @NotNull(message = "Seat Id required")
    private Integer seatId;

    @NotNull(message = "Film Time Id is required")
    private Integer filmTimeId;

    @NotNull(message = "Ticket Id is required")
    private Long ticketId;

    //@NotNull(message = "Description is required")
    private String description;

   // @NotNull(message = "Annotation is required")
    private String annotation;

    @NotNull(message = "Printed price is required")
    private Double printedPrice;

    //@NotNull(message = "Seat type is required")
    private Integer seatTypeId;

    @NotNull(message = "Vat is required")
    private Integer vatId;

    private Boolean scWeb;

    private Boolean scKiosk;

    private Boolean scPos;

   // @NotNull(message = "Start Date is required")
    private String startDate;

   // @NotNull(message = "End Date is required")
    private String endDate;

    private Date formattedStartDate;

    private Date formattedEndDate;

    private Boolean isChild;

    private Boolean isAdult;

    private Boolean status;

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getFilmTimeId() {
        return filmTimeId;
    }

    public void setFilmTimeId(Integer filmTimeId) {
        this.filmTimeId = filmTimeId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public Integer getVatId() {
        return vatId;
    }

    public void setVatId(Integer vatId) {
        this.vatId = vatId;
    }

    public Boolean getScWeb() {
        return scWeb;
    }

    public void setScWeb(Boolean scWeb) {
        this.scWeb = scWeb;
    }

    public Boolean getScKiosk() {
        return scKiosk;
    }

    public void setScKiosk(Boolean scKiosk) {
        this.scKiosk = scKiosk;
    }

    public Boolean getScPos() {
        return scPos;
    }

    public void setScPos(Boolean scPos) {
        this.scPos = scPos;
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

    public Boolean getIsChild() {
        return isChild;
    }

    public void setIsChild(Boolean isChild) {
        this.isChild = isChild;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
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
                "seatId=" + seatId +
                ", filmTimeId=" + filmTimeId +
                ", ticketId=" + ticketId +
                ", description='" + description + '\'' +
                ", annotation='" + annotation + '\'' +
                ", printedPrice=" + printedPrice +
                ", seatTypeId=" + seatTypeId +
                ", vatId=" + vatId +
                ", scWeb=" + scWeb +
                ", scKiosk=" + scKiosk +
                ", scPos=" + scPos +
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
