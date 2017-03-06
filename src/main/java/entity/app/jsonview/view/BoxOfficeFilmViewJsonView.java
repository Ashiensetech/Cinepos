package entity.app.jsonview.view;


import entity.app.jsonview.film.FilmImageJsonView;
import entity.app.jsonview.film.FilmTrailerJsonView;
import entity.app.jsonview.film.GenreJsonView;
import entity.app.jsonview.screen.ScreenDimensionJsonView;

/**
 * Created by mi on 3/6/17.
 */
public class BoxOfficeFilmViewJsonView {
    public interface Basic extends
            BoxOfficeFilmTimeViewJsonView.Summary,
            GenreJsonView.Basic{}
    public interface Summary extends Basic,
            ScreenDimensionJsonView.Summary,
            FilmImageJsonView.Summary,
            FilmTrailerJsonView.Summary{}
    public interface Details extends  Summary{}
}
