package validator.admin.AdminFilmScheduleService.createOrMerge;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.util.List;

/**
 * Created by mi on 1/24/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleForm{
    private int id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;
    private List<FilmTimeForm> filmTime;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<FilmTimeForm> getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(List<FilmTimeForm> filmTime) {
        this.filmTime = filmTime;
    }

    @Override
    public String toString() {
        return "ScheduleForm{" +
                "id=" + id +
                ", date=" + date +
                ", filmTime=" + filmTime +
                '}';
    }
}