package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 1/10/17.
 */
@Entity
@Table(name = "film")
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "distributor_id",referencedColumnName = "id")
    private Distributor distributor;


    @ManyToMany
    @JoinTable(name = "film_genre654", joinColumns = {
            @JoinColumn(name = "film_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "genre_id",
                    nullable = false, updatable = false) })
    private List<Genre> filmGenre;

    @OneToMany
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private List<FilmImage> filmImages;

    @OneToMany
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private List<FilmTrailer> filmTrailers;

    @Basic
    @Column(name = "rating")
    private float rating;

    @Basic
    @Column(name = "duration")
    private float duration;


    @Basic
    @Column(name = "status")
    private String status;


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



    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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


}
