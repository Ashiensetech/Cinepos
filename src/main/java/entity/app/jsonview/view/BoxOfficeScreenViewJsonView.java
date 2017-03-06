package entity.app.jsonview.view;

import entity.FilmSchedule;
import entity.app.jsonview.film.schedule.FilmScheduleJsonView;
import entity.app.jsonview.screen.ScreenJsonView;

/**
 * Created by mi on 3/6/17.
 */
public class BoxOfficeScreenViewJsonView {
    public interface Basic {}
    public interface Summary extends Basic,ScreenJsonView.Basic,
            BoxOfficeSchedulingViewJsonView.Summary,
            FilmScheduleJsonView.Details {}
    public interface Details extends  Summary{}
}
