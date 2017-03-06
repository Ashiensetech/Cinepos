package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.film.schedule.FilmScheduleJsonView;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by mi on 1/17/17.
 */
@Entity
@Table(name = "film_schedule")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilmSchedule {

    @JsonView(FilmScheduleJsonView.Basic.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonView(FilmScheduleJsonView.Basic.class)
    @Basic
    @Column(name = "date")
    private Date date;


    @JsonView(FilmScheduleJsonView.Details.class)
    @OneToOne
    @JoinColumn(name = "screen_id",referencedColumnName = "id")
    private Screen screen;


    @JsonView(FilmScheduleJsonView.Summary.class)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="film_schedule_id",referencedColumnName = "id")
    private Set<FilmTime> filmTimes;

    @JsonView(FilmScheduleJsonView.Summary.class)
    @Basic
    @Column(name = "status")
    private boolean status;



    @JsonView(FilmScheduleJsonView.Summary.class)
    @Basic
    @Column(name = "week_day")
    private int weekDay;

    @JsonIgnore
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;


    @JsonView(FilmScheduleJsonView.Summary.class)
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Set<FilmTime> getFilmTimes() {
        return filmTimes;
    }

    public void setFilmTimes(Set<FilmTime> filmTimes) {
        this.filmTimes = filmTimes;
    }


    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
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
        return "FilmSchedule{" +
                "id=" + id +
                ", screen=" + screen +
                ", filmTimes=" + filmTimes +
                ", status=" + status +
                ", date=" + date +
                ", weekDay=" + weekDay +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmSchedule that = (FilmSchedule) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (weekDay != that.weekDay) return false;
        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;
        if (filmTimes != null ? !filmTimes.equals(that.filmTimes) : that.filmTimes != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return !(createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        result = 31 * result + (filmTimes != null ? filmTimes.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + weekDay;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
