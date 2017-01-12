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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sunno on 1/11/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/film")
public class AdminFilmController {
    @Autowired
    FilmDao filmDao;

    @Autowired
    DistributorDao distributorDao;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    GenreDao genreDao;


    @RequestMapping(value = "/all")
    public ModelAndView allFilm(){
        List<Film> films = filmDao.getAll();
        ModelAndView mav =  new ModelAndView("web-admin/film/all-films");
        mav.addObject("films",films);
        return mav;
    }
    @RequestMapping(value = "/create")
    public ModelAndView createFilm(){
        List<Distributor> distributors = distributorDao.getAll();
        List<ScreenDimension> screenDimensions = screenDimensionDao.getAll();
        ModelAndView mav =  new ModelAndView("web-admin/film/create-film");
        List<Genre> genres = genreDao.getAllAlphabetically();
        mav.addObject("screenDimensions",screenDimensions);
        mav.addObject("distributors",distributors);
        mav.addObject("genres",genres);
        return mav;
    }
}
