package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sunno on 1/11/17.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/film")
public class AdminFilmController {
    @Autowired
    FilmDao filmDao;
}
