package validator.admin.AdminFilmService.editFilm;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by sunno on 1/11/17.
 */
public class EditFilmForm {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotBlank(message = "Name required")
    @Length(max=50,message = "Name too large")
    private String name;

    @NotNull(message = "Distributor is required")
    private Integer distributorId;

    @NotNull(message = "Rating is required")
    private Float rating;

    @NotNull(message = "Duration is required")
    private Float duration;

    @NotNull(message = "Status is required")
    private boolean status;

    @NotNull(message = "Price Shift is required")
    private boolean isPriceShift;

    //    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull(message = "Start Time is required")
    private String startDate;

    //    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull(message = "End Date is required")
    private String endDate;


    private Date formattedStartDate;

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

    private Date formattedEndDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getIsPriceShift() {
        return isPriceShift;
    }

    public void setIsPriceShift(boolean priceShift) {
        isPriceShift = priceShift;
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

    @Override
    public String toString() {
        return "EditFilmFrom{" +
                "name='" + name + '\'' +
                ", distributorId=" + distributorId +
                ", rating=" + rating +
                ", duration=" + duration +
                ", status=" + status +
                ", isPriceShift=" + isPriceShift +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
