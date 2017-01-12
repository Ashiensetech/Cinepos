package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/12/17.
 */
@Entity
@Table(name = "film_screen_type")
public class FilmScreenType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "flim_id")
    private int filmId;

    @Basic
    @Column(name = "screen_dimension_id")
    private int screenDimensionId;

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

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getScreenDimensionId() {
        return screenDimensionId;
    }

    public void setScreenDimensionId(int screenDimensionId) {
        this.screenDimensionId = screenDimensionId;
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

        FilmScreenType that = (FilmScreenType) o;

        if (id != that.id) return false;
        if (filmId != that.filmId) return false;
        if (screenDimensionId != that.screenDimensionId) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + filmId;
        result = 31 * result + screenDimensionId;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
