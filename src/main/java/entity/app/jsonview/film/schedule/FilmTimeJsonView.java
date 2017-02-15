package entity.app.jsonview.film.schedule;

import entity.app.jsonview.film.FilmJsonView;

/**
 * Created by mi on 2/15/17.
 */
public class FilmTimeJsonView {
    public interface Basic {}
    public interface Summary extends Basic,FilmJsonView.Basic {}
    public interface Details extends FilmJsonView.Summary{}
}
