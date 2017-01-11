package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import dao.FilmDao;
import entity.Distributor;
import entity.Film;
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
        ModelAndView mav =  new ModelAndView("web-admin/film/create-film");
        mav.addObject("distributors",distributors);
        return mav;
    }
}
