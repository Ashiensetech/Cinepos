package entity.app.jsonview.screen;

import entity.Screen;
import entity.app.jsonview.view.BoxOfficeSchedulingViewJsonView;

/**
 * Created by mi on 3/6/17.
 */
public class ScreenBoxOfficeJsonView {
    public interface Basic {}
    public interface Summary extends Basic, ScreenJsonView.Basic, BoxOfficeSchedulingViewJsonView.Summary {}
    public interface Details extends  Summary{}
}
