package entity.iface.film.schedule;

import com.fasterxml.jackson.annotation.JsonView;
import entity.Screen;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by mi on 2/13/17.
 */

public interface FilmScheduleBasicIface {
    @JsonView(FilmScheduleBasicIface.class)
    int getId();
    @JsonView(FilmScheduleBasicIface.class)
    Date getDate();
}
