package entity.app.jsonview.view;

import entity.app.jsonview.screen.ScreenJsonView;

/**
 * Created by mi on 3/6/17.
 */
public class BoxOfficeSchedulingViewJsonView {
    public interface Basic {}
    public interface Summary extends Basic,BoxOfficeFilmViewJsonView.Basic{}
    public interface Details extends  Summary{}
}
