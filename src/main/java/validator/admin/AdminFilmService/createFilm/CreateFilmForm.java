package validator.admin.AdminFilmService.createFilm;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
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
    @Min(value = 0, message = "Rating can't be negative")
    private Float rating;

    @NotNull(message = "Duration hour is required")
    @Min(value = 0, message = "Duration hour can't be negative")
    private Integer durationHour;

    @NotNull(message = "Duration min is required")
    @Min(value = 0, message = "Duration min can't be negative")
    private Integer durationMin;

    @NotNull(message = "Status is required")
    private boolean status;

    @NotNull(message = "Price Shift is required")
    private boolean isPriceShift;

    @NotNull(message = "Trailer is required")
    private String trailer;

    @NotNull(message = "Start Time is required")
    private String startDate;

    @NotNull(message = "End Date is required")
    private String endDate;

    @NotNull(message = "Film screen type required")
    @NotBlank(message = "Film screen type required")
    private String screenDimensions;

    @NotNull(message = "Banner Image is required")
    private Integer bannerImageToken;

    @NotNull(message = "Film genre  required")
    @NotBlank(message = "Film genre required")
    private String genreIds;

    private List<Integer> filmGenreIdList = new ArrayList<>();

    private String otherImagesToken;

    private List<Integer> otherImagesTokenArray = new ArrayList<>();

    private Date formattedStartDate;

    private Date formattedEndDate;

    private List<Integer> screenDimensionIdList;



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

    public Integer getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(Integer durationMin) {
        this.durationMin = durationMin;
    }

    public Integer getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
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

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
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


    public String getScreenDimensions() {
        return screenDimensions;
    }

    public void setScreenDimensions(String screenDimensions) {
        this.screenDimensions = screenDimensions;
    }

    public List<Integer> getScreenDimensionIdList() {
        return screenDimensionIdList;
    }

    public void setScreenDimensionIdList(List<Integer> screenDimensionIdList) {
        this.screenDimensionIdList = screenDimensionIdList;
    }

    public String getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(String genreIds) {
        this.genreIds = genreIds;
    }

    public List<Integer> getFilmGenreIdList() {
        return filmGenreIdList;
    }

    public void setFilmGenreIdList(List<Integer> filmGenreIdList) {
        this.filmGenreIdList = filmGenreIdList;
    }

    @Override
    public String toString() {
        return "CreateFilmForm{" +
                "name='" + name + '\'' +
                ", distributorId=" + distributorId +
                ", rating=" + rating +
                ", durationHour=" + durationHour +
                ", durationMin=" + durationMin +
                ", status=" + status +
                ", isPriceShift=" + isPriceShift +
                ", trailer='" + trailer + '\'' +
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
