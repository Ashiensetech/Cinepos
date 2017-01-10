package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/10/17.
 */
@Entity
@Table(name = "film_genre")
public class FilmGenre {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "film_id")
    private Integer filmId;

    @Basic
    @Column(name = "genre_id")
    private Integer genreId;

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


    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }


    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
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

        FilmGenre filmGenre = (FilmGenre) o;

        if (id != filmGenre.id) return false;
        if (filmId != null ? !filmId.equals(filmGenre.filmId) : filmGenre.filmId != null) return false;
        if (genreId != null ? !genreId.equals(filmGenre.genreId) : filmGenre.genreId != null) return false;
        if (createdBy != null ? !createdBy.equals(filmGenre.createdBy) : filmGenre.createdBy != null) return false;
        if (createdAt != null ? !createdAt.equals(filmGenre.createdAt) : filmGenre.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (filmId != null ? filmId.hashCode() : 0);
        result = 31 * result + (genreId != null ? genreId.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
