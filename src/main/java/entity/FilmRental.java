package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by mi on 1/23/17.
 */
@Entity
@Table(name = "film_rental")
public class FilmRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    private Film film;

    @Basic
    @Column(name = "week_name")
    private String weekName;

    @Basic
    @Column(name = "start_date")
    private Date startDate;

    @Basic
    @Column(name = "end_date")
    private Date endDate;

    @Basic
    @Column(name = "created_by")
    private Integer createdBy;

    @Basic
    @Column(name = "created_date")
    private Timestamp createdDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Film getFilmId() {
        return film;
    }

    public void setFilmId(Film film) {
        this.film = film;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
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


    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmRental that = (FilmRental) o;

        if (id != that.id) return false;
        if (film != null ? !film.equals(that.film) : that.film != null) return false;
        if (weekName != null ? !weekName.equals(that.weekName) : that.weekName != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return !(createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (film != null ? film.hashCode() : 0);
        result = 31 * result + (weekName != null ? weekName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
