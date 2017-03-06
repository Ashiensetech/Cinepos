package entity.app.jsonview.film.schedule;

import entity.FilmTime;
import entity.app.jsonview.screen.ScreenJsonView;

/**
 * Created by mi on 2/14/17.
 */
public class FilmScheduleJsonView {
    public interface Basic {}
    public interface Summary extends Basic, FilmTimeJsonView.Summary {}
    public interface Details extends  Summary,ScreenJsonView.Summary{}
}
