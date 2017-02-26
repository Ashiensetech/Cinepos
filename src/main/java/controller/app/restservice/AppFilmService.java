package controller.app.restservice;


import com.fasterxml.jackson.annotation.JsonView;
import controller.app.AppUriPreFix;
import dao.FilmDao;
import entity.Film;
import entity.app.jsonview.film.FilmJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mi on 1/2/17.
 */

@RestController
@RequestMapping(AppUriPreFix.apiUriPrefix +"/film")
public class AppFilmService {
    @Autowired
    FilmDao filmDao;


    @JsonView(FilmJsonView.Basic.class)
    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam Integer limit,@RequestParam Integer offset) {


        List<Film> films = filmDao.getAllActive(limit,offset);

        if(films==null || films.size()==0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(films);
    }
    @JsonView(FilmJsonView.Summary.class)
    @RequestMapping(value = "/get/{filmId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer filmId) {


        Film film = filmDao.getById(filmId);

        if(film==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(film);
    }

}
