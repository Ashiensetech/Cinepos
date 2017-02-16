package entity.app.jsonview.ticket;

import entity.app.jsonview.film.FilmImageJsonView;
import entity.app.jsonview.film.FilmTrailerJsonView;
import entity.app.jsonview.film.GenreJsonView;
import entity.app.jsonview.film.schedule.FilmTimeJsonView;
import entity.app.jsonview.screen.ScreenDimensionJsonView;
import entity.app.jsonview.screen.ScreenSeatJsonView;
import entity.app.jsonview.vat.VatSettingsJsonView;

/**
 * Created by mi on 2/15/17.
 */
public class TicketJsonView {

    public interface Basic {}
    public interface Summary extends
            Basic,
            FilmTimeJsonView.Summary,
            VatSettingsJsonView.Summary,
            ScreenSeatJsonView.Summary {}
    public interface Details extends  Summary{}
}
