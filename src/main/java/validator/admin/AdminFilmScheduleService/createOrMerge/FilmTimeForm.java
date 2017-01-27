package validator.admin.AdminFilmScheduleService.createOrMerge;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by mi on 1/24/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmTimeForm{
    private int id;
    private int filmId;
    private int scheduleId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private Time startTime;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private Time endTime;

    @NotBlank(message = "Start time required")
    private String sTime;
    @NotBlank(message = "End time required")
    private String eTime;

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


    public String getsTime() {

        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public String toString() {
        return "FilmTimeForm{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", scheduleId=" + scheduleId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
