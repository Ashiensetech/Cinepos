package entity.tableview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.view.BoxOfficeFilmTimeViewJsonView;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by mi on 2/7/17.
 */

@Entity
@Immutable
@Table(name = "BOX_OFFICE_TIME_VIEW")
public class BoxOfficeFilmTimeView {

    @JsonView(BoxOfficeFilmTimeViewJsonView.Basic.class)
    @Id
    @Column(name = "id")
    private int id;

    @JsonView(BoxOfficeFilmTimeViewJsonView.Basic.class)
    @Basic
    @Column(name = "film_schedule_id")
    private Integer filmScheduleId;

    @JsonView(BoxOfficeFilmTimeViewJsonView.Basic.class)
    @Basic
    @Column(name = "start_time")
    private Time startTime;

    @JsonView(BoxOfficeFilmTimeViewJsonView.Basic.class)
    @Basic
    @Column(name = "end_time")
    private Time endTime;

    @JsonView(BoxOfficeFilmTimeViewJsonView.Summary.class)
    @Basic
    @Column(name = "status")
    private boolean status;

    @JsonIgnore
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;

    @JsonView(BoxOfficeFilmTimeViewJsonView.Details.class)
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFilmScheduleId() {
        return filmScheduleId;
    }

    public void setFilmScheduleId(Integer filmScheduleId) {
        this.filmScheduleId = filmScheduleId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

        BoxOfficeFilmTimeView that = (BoxOfficeFilmTimeView) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (filmScheduleId != null ? !filmScheduleId.equals(that.filmScheduleId) : that.filmScheduleId != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return !(createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (filmScheduleId != null ? filmScheduleId.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BoxOfficeFilmTimeView{" +
                "id=" + id +
                ", filmScheduleId=" + filmScheduleId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
