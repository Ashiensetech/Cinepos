package entity.compositive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import entity.FilmSchedule;
import entity.Screen;
import entity.app.jsonview.view.BoxOfficeScreenViewJsonView;
import entity.tableview.BoxOfficeSchedulingView;

import java.util.List;

/**
 * Created by mi on 3/6/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BoxOfficeScreen {

    @JsonView(BoxOfficeScreenViewJsonView.Summary.class)
    private Screen screen;

    @JsonView(BoxOfficeScreenViewJsonView.Summary.class)
    private List<BoxOfficeSchedulingView> boxOffice;

    @JsonView(BoxOfficeScreenViewJsonView.Summary.class)
    private FilmSchedule filmSchedule;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public List<BoxOfficeSchedulingView> getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(List<BoxOfficeSchedulingView> boxOffice) {
        this.boxOffice = boxOffice;
    }

    public FilmSchedule getFilmSchedule() {
        return filmSchedule;
    }

    public void setFilmSchedule(FilmSchedule filmSchedule) {
        this.filmSchedule = filmSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoxOfficeScreen that = (BoxOfficeScreen) o;

        if (screen != null ? !screen.equals(that.screen) : that.screen != null) return false;
        if (boxOffice != null ? !boxOffice.equals(that.boxOffice) : that.boxOffice != null) return false;
        return !(filmSchedule != null ? !filmSchedule.equals(that.filmSchedule) : that.filmSchedule != null);

    }

    @Override
    public int hashCode() {
        int result = screen != null ? screen.hashCode() : 0;
        result = 31 * result + (boxOffice != null ? boxOffice.hashCode() : 0);
        result = 31 * result + (filmSchedule != null ? filmSchedule.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BoxOfficeScreen{" +
                "screen=" + screen +
                ", boxOffice=" + boxOffice +
                ", filmSchedule=" + filmSchedule +
                '}';
    }
}
