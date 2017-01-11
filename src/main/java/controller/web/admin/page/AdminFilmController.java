package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sunno on 1/11/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/film")
public class AdminFilmController {
    @Autowired
    FilmDao filmDao;

    @RequestMapping(value = "/all")
    public ModelAndView allFilm(){
        ModelAndView mav =  new ModelAndView("web-admin/film/all-films");
        return mav;
    }
    @RequestMapping(value = "/create")
    public ModelAndView createFilm(){
        ModelAndView mav =  new ModelAndView("web-admin/film/create-film");
        return mav;
    }
}
