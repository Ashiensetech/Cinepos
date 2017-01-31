package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.*;
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
 * Created by mi on 1/17/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/film-scheduling")
public class AdminFilmSchedulingController {
    @Autowired
    FilmDao filmDao;

    @Autowired
    DistributorDao distributorDao;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    ScreenDao screeDao;

    @RequestMapping(value = "/all")
    public ModelAndView allSchedule(){
        ModelAndView mav =  new ModelAndView("web-admin/film-scheduling/all-scheduling");

        mav.addObject("screens", screeDao.getAllActivated());
        mav.addObject("films",filmDao.getAllActive());
        return mav;
    }
    @RequestMapping(value = "/manage")
    public ModelAndView createSchedule(){
        ModelAndView mav =  new ModelAndView("web-admin/film-scheduling/create-scheduling");

        mav.addObject("screens", screeDao.getAllActivated());
        mav.addObject("films",filmDao.getAllActive());
        return mav;
    }

}
