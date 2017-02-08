package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import dao.FilmDao;
import dao.GenreDao;
import dao.ScreenDimensionDao;
import dao.reportDao.ConcessionSalesByOperatorViewDao;
import entity.Distributor;
import entity.Film;
import entity.Genre;
import entity.ScreenDimension;
import entity.entityView.report.ConcessionSalesByOperatorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/report")
public class AdminReportController {
    @Autowired
    FilmDao filmDao;

    @Autowired
    DistributorDao distributorDao;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    ConcessionSalesByOperatorViewDao concessionSalesByOperatorViewDao;


    @RequestMapping(value = "/performance")
    public ModelAndView performance(){
        ModelAndView mav =  new ModelAndView("web-admin/report/performance");
        return mav;
    }









    @RequestMapping(value = "/product-sells-by-operator")
    public ModelAndView productSellsByOperator(){
        List<ConcessionSalesByOperatorView> concessionSalesByOperatorList=concessionSalesByOperatorViewDao.getAll();
        ModelAndView modelAndView =  new ModelAndView("web-admin/report/product-sells-by-operator");
        modelAndView.addObject("productSalesByOperatorList",concessionSalesByOperatorList);
        return modelAndView;
    }


}
