package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.app.jsonview.film.FilmJsonView;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by mi on 1/10/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "film")
public class Film {


    @JsonView(FilmJsonView.Basic.class)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "name")
    private String name;

    @JsonView(FilmJsonView.Details.class)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_id",referencedColumnName = "id")
    private Distributor distributor;

    @JsonView(FilmJsonView.Summary.class)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_genre", joinColumns = {
            @JoinColumn(name = "film_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "genre_id",
                    nullable = false, updatable = false) })
    private List<Genre> filmGenre;

    @JsonView(FilmJsonView.Summary.class)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_screen_type", joinColumns = {
            @JoinColumn(name = "film_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "screen_dimension_id",
                    nullable = false, updatable = false) })
    private Set<ScreenDimension> screenDimensions;

    @JsonView(FilmJsonView.Summary.class)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private List<FilmImage> filmImages;

    @JsonView(FilmJsonView.Summary.class)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private List<FilmTrailer> filmTrailers;

    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "rating")
    private float rating;

    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "duration_hour")
    private float durationHour;

    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "duration_min")
    private float durationMin;


    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "status")
    private Boolean status;

    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "start_date")
    private Date startDate;

    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "end_date")
    private Date endDate;

    @JsonView(FilmJsonView.Basic.class)
    @Basic
    @Column(name = "is_price_shift")
    private boolean isPriceShift;

    @JsonIgnore
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;

    @JsonView(FilmJsonView.Basic.class)
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


    public Distributor getDistributor() {
        return distributor;
    }

    public void setDistributor(Distributor distributor) {
        this.distributor = distributor;
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


    public boolean getIsPriceShift() {
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
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", distributor=" + distributor +
                ", filmGenre=" + filmGenre +
                ", screenDimensions=" + screenDimensions +
                ", filmImages=" + filmImages +
                ", filmTrailers=" + filmTrailers +
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (Float.compare(film.rating, rating) != 0) return false;
        if (Float.compare(film.durationHour, durationHour) != 0) return false;
        if (Float.compare(film.durationMin, durationMin) != 0) return false;
        if (isPriceShift != film.isPriceShift) return false;
        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        if (distributor != null ? !distributor.equals(film.distributor) : film.distributor != null) return false;
        if (filmGenre != null ? !filmGenre.equals(film.filmGenre) : film.filmGenre != null) return false;
        if (screenDimensions != null ? !screenDimensions.equals(film.screenDimensions) : film.screenDimensions != null)
            return false;
        if (filmImages != null ? !filmImages.equals(film.filmImages) : film.filmImages != null) return false;
        if (filmTrailers != null ? !filmTrailers.equals(film.filmTrailers) : film.filmTrailers != null) return false;
        if (status != null ? !status.equals(film.status) : film.status != null) return false;
        if (startDate != null ? !startDate.equals(film.startDate) : film.startDate != null) return false;
        if (endDate != null ? !endDate.equals(film.endDate) : film.endDate != null) return false;
        if (createdBy != null ? !createdBy.equals(film.createdBy) : film.createdBy != null) return false;
        return !(createdAt != null ? !createdAt.equals(film.createdAt) : film.createdAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (distributor != null ? distributor.hashCode() : 0);
        result = 31 * result + (filmGenre != null ? filmGenre.hashCode() : 0);
        result = 31 * result + (screenDimensions != null ? screenDimensions.hashCode() : 0);
        result = 31 * result + (filmImages != null ? filmImages.hashCode() : 0);
        result = 31 * result + (filmTrailers != null ? filmTrailers.hashCode() : 0);
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
}
