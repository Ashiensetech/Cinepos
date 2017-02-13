package entity.iface.film.schedule;

import com.fasterxml.jackson.annotation.JsonView;
import entity.FilmTime;
import entity.Screen;
import entity.iface.ScreenSummaryIfaceApp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by mi on 2/13/17.
 */

public interface FilmScheduleSummaryIface extends FilmScheduleBasicIface{

    @JsonView(FilmScheduleSummaryIface.class)
    ScreenSummaryIfaceApp getScreen();

    @JsonView(FilmScheduleSummaryIface.class)
    boolean getStatus();

    @JsonView(FilmScheduleSummaryIface.class)
    int getWeekDay();

    @JsonView(FilmScheduleSummaryIface.class)
    Integer getCreatedBy();

    @JsonView(FilmScheduleSummaryIface.class)
    Timestamp getCreatedAt();

    @JsonView(FilmScheduleSummaryIface.class)
    Set<FilmTime> getFilmTimes();
}
