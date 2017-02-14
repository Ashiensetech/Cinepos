package entity.app.jsonview.film;

import entity.app.jsonview.screen.ScreenJsonView;

/**
 * Created by mi on 2/14/17.
 */
public class FilmScheduleJsonView {
    public interface CompositeScreen extends ScreenJsonView.Basic{}

    public interface Basic {}
    public interface Summary extends Basic {}
    public interface Details extends  Summary,CompositeScreen{}
}
