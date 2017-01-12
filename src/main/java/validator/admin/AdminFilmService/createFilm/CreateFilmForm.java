package validator.admin.AdminFilmService.createFilm;

import entity.Distributor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sunno on 1/11/17.
 */
public class CreateFilmForm {


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


    @NotNull(message = "Banner Image is required")
    private Integer bannerImageToken;

    private String otherImagesToken;

    private List<Integer> otherImagesTokenArray = new ArrayList<>();

    private Date formattedStartDate;

    private Date formattedEndDate;





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

    public Integer getBannerImageToken() {
        return bannerImageToken;
    }

    public void setBannerImageToken(Integer bannerImageToken) {
        this.bannerImageToken = bannerImageToken;
    }

    public String getOtherImagesToken() {
        return otherImagesToken;
    }

    public void setOtherImagesToken(String otherImagesToken) {
        this.otherImagesToken = otherImagesToken;
    }


    public List<Integer> getOtherImagesTokenArray() {
        return otherImagesTokenArray;
    }

    public void setOtherImagesTokenArray(List<Integer> otherImagesTokenArray) {
        this.otherImagesTokenArray = otherImagesTokenArray;
    }

    @Override
    public String toString() {
        return "CreateFilmForm{" +
                "name='" + name + '\'' +
                ", distributorId=" + distributorId +
                ", rating=" + rating +
                ", duration=" + duration +
                ", status=" + status +
                ", isPriceShift=" + isPriceShift +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", bannerImageToken=" + bannerImageToken +
                ", otherImagesToken='" + otherImagesToken + '\'' +
                ", otherImagesTokenArray=" + otherImagesTokenArray +
                ", formattedStartDate=" + formattedStartDate +
                ", formattedEndDate=" + formattedEndDate +
                '}';
    }
}
