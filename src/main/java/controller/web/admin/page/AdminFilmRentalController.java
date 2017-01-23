package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.FilmDao;
import dao.FilmRentalDao;
import entity.Film;
import entity.FilmRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by sunno on 1/9/17.
 */

@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/film-rental")
public class AdminFilmRentalController {

    @Autowired
    FilmRentalDao filmRentalDao;

    @Autowired
    FilmDao filmDao;

    @RequestMapping(value = "/create")
    public ModelAndView createFilmRentalPage(){
        ModelAndView mav =  new ModelAndView("web-admin/film-rental/create-film-rental");
        List<Film> films = filmDao.getAll();
        mav.addObject("films",films);
        return mav;
    }

    @RequestMapping("/all")
    public ModelAndView allFilmRentalPage(){
        ModelAndView mav = new ModelAndView("web-admin/film-rental/all-film-rental");
        List<FilmRental> filmRentals = filmRentalDao.getAll();
        mav.addObject("filmRentals",filmRentals);
        return mav;
    }

    @RequestMapping("/edit/{filmRentalId}")
    public ModelAndView editFilmRentalPage(Authentication authentication, @PathVariable Integer filmRentalId){
        ModelAndView mav =  new ModelAndView("web-admin/film-rental/edit-film-rental");
        List<Film> films = filmDao.getAll();
        mav.addObject("films",films);
        FilmRental filmRental = filmRentalDao.getById(filmRentalId);
        mav.addObject("filmRental",filmRental);
        return mav;
    }


}

