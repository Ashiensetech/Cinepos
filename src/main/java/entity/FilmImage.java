package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.film.FilmImageJsonView;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/10/17.
 */
@Entity
@Table(name = "film_image")
public class FilmImage {

    @JsonView(FilmImageJsonView.Basic.class)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(FilmImageJsonView.Summary.class)
    @Basic
    @Column(name = "film_id")
    private Integer filmId;

    @JsonView(FilmImageJsonView.Basic.class)
    @Basic
    @Column(name = "file_path")
    private String filePath;

    @JsonView(FilmImageJsonView.Basic.class)
    @Basic
    @Column(name = "is_banner")
    private boolean isBanner;

    @JsonView(FilmImageJsonView.Basic.class)
    @Basic
    @Column(name = "width")
    private int width;

    @JsonView(FilmImageJsonView.Basic.class)
    @Basic
    @Column(name = "height")
    private int height;

    @JsonIgnore
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;

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

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public boolean getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(boolean isBanner) {
        this.isBanner = isBanner;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
