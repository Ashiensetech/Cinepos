package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import dao.FilmDao;
import dao.GenreDao;
import dao.ScreenDimensionDao;
import entity.Distributor;
import entity.Film;
import entity.Genre;
import entity.ScreenDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sunno on 1/11/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/genre")
public class AdminGenreController {

    @Autowired
    GenreDao genreDao;


    @RequestMapping(value = "/all")
    public ModelAndView allGenre(){
        ModelAndView mav =  new ModelAndView("web-admin/genre/all-genre");
        List<Genre> genres = genreDao.getAll();
        mav.addObject("genres",genres);
        return mav;
    }
    @RequestMapping(value = "/create")
    public ModelAndView createGenre(){
        ModelAndView mav =  new ModelAndView("web-admin/genre/create-genre");
        return mav;
    }
    @RequestMapping(value = "/edit/{genreId}")
    public ModelAndView createGenre(@PathVariable Integer genreId){
        ModelAndView mav =  new ModelAndView("web-admin/genre/edit-genre");

        Genre genre = genreDao.getById(genreId);
        mav.addObject("genre",genre);
        return mav;
    }
}
