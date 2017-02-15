package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.film.FilmTrailerJsonView;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/10/17.
 */
@Entity
@Table(name = "film_trailer")
public class FilmTrailer {

    @JsonView(FilmTrailerJsonView.Basic.class)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @Basic
    @Column(name = "film_id")
    private int filmId;

    @JsonView(FilmTrailerJsonView.Basic.class)
    @Basic
    @Column(name = "trailer_url")
    private String trailerUrl;

    @JsonIgnore
    @Basic
    @Column(name = "created_by")
    private int createdBy;

    @JsonIgnore
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int flimId) {
        this.filmId = flimId;
    }


    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
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

        FilmTrailer that = (FilmTrailer) o;

        if (id != that.id) return false;
        if (filmId != that.filmId) return false;
        if (createdBy != that.createdBy) return false;
        if (trailerUrl != null ? !trailerUrl.equals(that.trailerUrl) : that.trailerUrl != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + filmId;
        result = 31 * result + (trailerUrl != null ? trailerUrl.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
