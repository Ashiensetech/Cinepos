package entity.entityView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.Film;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;

/**
 * Created by mi on 2/7/17.
 */
@Entity
@Immutable
@Table(name = "BOX_OFFICE_SCHEDULING_VIEW")
public class BoxOfficeSchedulingView {

    @Id
    @Column(name = "id")
    int id;

    @OneToOne
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    BoxOfficeFilmView film;

    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BoxOfficeFilmView getFilm() {
        return film;
    }

    public void setFilm(BoxOfficeFilmView film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "BoxOfficeSchedulingView{" +
                "film=" + film +
                '}';
    }
}
