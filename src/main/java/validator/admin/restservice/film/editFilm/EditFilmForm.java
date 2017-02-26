package validator.admin.restservice.film.editFilm;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sunno on 1/11/17.
 */
public class EditFilmForm {

    private int id;

    private String name;

    private Integer distributorId;

    private Float rating;

    private Integer durationHour;

    private Integer durationMin;

    private boolean status;

    private boolean isPriceShift;

    private String startDate;

    private String endDate;

    private String genreIds;

    private List<Integer> filmGenreIdList = new ArrayList<>();

    private Integer bannerImageToken;

    private String otherImagesToken;

    private List<Integer> otherImagesTokenArray = new ArrayList<>();

    private Date formattedStartDate;

    private Date formattedEndDate;

    private String trailer;

    private String screenDimensions;

    private Set<Integer> screenDimensionIdList = new HashSet<>();

    private String deletedImagesIds;

    private Set<Integer> deletedImagesIdSet = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
    }

    public Integer getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(Integer durationMin) {
        this.durationMin = durationMin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isPriceShift() {
        return isPriceShift;
    }

    public void setIsPriceShift(boolean isPriceShift) {
        this.isPriceShift = isPriceShift;
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

    public Set<Integer> getScreenDimensionIdList() {
        return screenDimensionIdList;
    }

    public void setScreenDimensionIdList(Set<Integer> screenDimensionIdList) {
        this.screenDimensionIdList = screenDimensionIdList;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getScreenDimensions() {
        return screenDimensions;
    }

    public void setScreenDimensions(String screenDimensions) {
        this.screenDimensions = screenDimensions;
    }

    public String getDeletedImagesIds() {
        return deletedImagesIds;
    }

    public void setDeletedImagesIds(String deletedImagesIds) {
        this.deletedImagesIds = deletedImagesIds;
    }

    public Set<Integer> getDeletedImagesIdSet() {
        return deletedImagesIdSet;
    }

    public void setDeletedImagesIdSet(Set<Integer> deletedImagesIdSet) {
        this.deletedImagesIdSet = deletedImagesIdSet;
    }

    @Override
    public String toString() {
        return "EditFilmForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", distributorId=" + distributorId +
                ", rating=" + rating +
                ", durationHour=" + durationHour +
                ", durationMin=" + durationMin +
                ", status=" + status +
                ", isPriceShift=" + isPriceShift +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", genreIds='" + genreIds + '\'' +
                ", filmGenreIdList=" + filmGenreIdList +
                ", otherImagesToken='" + otherImagesToken + '\'' +
                ", otherImagesTokenArray=" + otherImagesTokenArray +
                ", formattedStartDate=" + formattedStartDate +
                ", formattedEndDate=" + formattedEndDate +
                ", trailer='" + trailer + '\'' +
                ", screenDimensions='" + screenDimensions + '\'' +
                ", screenDimensionIdList=" + screenDimensionIdList +
                '}';
    }
}
