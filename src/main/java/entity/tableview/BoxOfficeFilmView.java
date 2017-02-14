package entity.tableview;

import entity.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by mi on 2/7/17.
 */

@Entity
@Immutable
@Table(name = "BOX_OFFICE_FILM_VIEW")
public class BoxOfficeFilmView {
    @Id
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "film_genre", joinColumns = {
            @JoinColumn(name = "film_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "genre_id",
                    nullable = false, updatable = false) })
    private List<Genre> filmGenre;

    @ManyToMany
    @JoinTable(name = "film_screen_type", joinColumns = {
            @JoinColumn(name = "film_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "screen_dimension_id",
                    nullable = false, updatable = false) })
    private Set<ScreenDimension> screenDimensions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private List<FilmImage> filmImages;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private List<FilmTrailer> filmTrailers;


    @OneToMany//(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    List<BoxOfficeFilmTimeView> filmTimes;

    @Basic
    @Column(name = "rating")
    private float rating;

    @Basic
    @Column(name = "duration_hour")
    private float durationHour;


    @Basic
    @Column(name = "duration_min")
    private float durationMin;



    @Basic
    @Column(name = "status")
    private Boolean status;


    @Basic
    @Column(name = "start_date")
    private Date startDate;


    @Basic
    @Column(name = "end_date")
    private Date endDate;


    @Basic
    @Column(name = "is_price_shift")
    private boolean isPriceShift;


    @Basic
    @Column(name = "created_by")
    private Integer createdBy;


    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;


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

    public List<Genre> getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(List<Genre> filmGenre) {
        this.filmGenre = filmGenre;
    }

    public Set<ScreenDimension> getScreenDimensions() {
        return screenDimensions;
    }

    public void setScreenDimensions(Set<ScreenDimension> screenDimensions) {
        this.screenDimensions = screenDimensions;
    }

    public List<FilmImage> getFilmImages() {
        return filmImages;
    }

    public void setFilmImages(List<FilmImage> filmImages) {
        this.filmImages = filmImages;
    }

    public List<FilmTrailer> getFilmTrailers() {
        return filmTrailers;
    }

    public void setFilmTrailers(List<FilmTrailer> filmTrailers) {
        this.filmTrailers = filmTrailers;
    }

    public List<BoxOfficeFilmTimeView> getFilmTimes() {
        return filmTimes;
    }

    public void setFilmTimes(List<BoxOfficeFilmTimeView> filmTimes) {
        this.filmTimes = filmTimes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getDurationHour() {
        return durationHour;
    }

    public void setDurationHour(float durationHour) {
        this.durationHour = durationHour;
    }

    public float getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(float durationMin) {
        this.durationMin = durationMin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isPriceShift() {
        return isPriceShift;
    }

    public void setIsPriceShift(boolean isPriceShift) {
        this.isPriceShift = isPriceShift;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {


        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxOfficeFilmView that = (BoxOfficeFilmView) o;

        if (id != that.id) return false;
        if (Float.compare(that.rating, rating) != 0) return false;
        if (Float.compare(that.durationHour, durationHour) != 0) return false;
        if (Float.compare(that.durationMin, durationMin) != 0) return false;
        if (isPriceShift != that.isPriceShift) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (filmGenre != null ? !filmGenre.equals(that.filmGenre) : that.filmGenre != null) return false;
        if (screenDimensions != null ? !screenDimensions.equals(that.screenDimensions) : that.screenDimensions != null)
            return false;
        if (filmImages != null ? !filmImages.equals(that.filmImages) : that.filmImages != null) return false;
        if (filmTrailers != null ? !filmTrailers.equals(that.filmTrailers) : that.filmTrailers != null) return false;
        if (filmTimes != null ? !filmTimes.equals(that.filmTimes) : that.filmTimes != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return !(createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (filmGenre != null ? filmGenre.hashCode() : 0);
        result = 31 * result + (screenDimensions != null ? screenDimensions.hashCode() : 0);
        result = 31 * result + (filmImages != null ? filmImages.hashCode() : 0);
        result = 31 * result + (filmTrailers != null ? filmTrailers.hashCode() : 0);
        result = 31 * result + (filmTimes != null ? filmTimes.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        result = 31 * result + (durationHour != +0.0f ? Float.floatToIntBits(durationHour) : 0);
        result = 31 * result + (durationMin != +0.0f ? Float.floatToIntBits(durationMin) : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (isPriceShift ? 1 : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BoxOfficeFilmView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filmGenre=" + filmGenre +
                ", screenDimensions=" + screenDimensions +
                ", filmImages=" + filmImages +
                ", filmTrailers=" + filmTrailers +
                ", filmTimes=" + filmTimes +
                ", rating=" + rating +
                ", durationHour=" + durationHour +
                ", durationMin=" + durationMin +
                ", status=" + status +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPriceShift=" + isPriceShift +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
