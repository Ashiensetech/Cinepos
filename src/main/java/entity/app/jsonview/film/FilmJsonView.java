package entity.app.jsonview.film;

import entity.app.jsonview.screen.ScreenDimensionJsonView;

/**
 * Created by mi on 2/15/17.
 */
public class FilmJsonView {

    public interface Basic {}
    public interface Summary extends
            Basic,
            ScreenDimensionJsonView.Basic,
            GenreJsonView.Basic,
            FilmImageJsonView.Basic,
            FilmTrailerJsonView.Basic{}
    public interface Details extends  Summary{}
}
