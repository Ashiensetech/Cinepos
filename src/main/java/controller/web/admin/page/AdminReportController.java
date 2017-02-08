package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import dao.FilmDao;
import dao.GenreDao;
import dao.ScreenDimensionDao;
import dao.reportDao.ConcessionSalesByOperatorViewDao;
import dao.reportDao.ProductSummaryReportViewDao;
import entity.Distributor;
import entity.Film;
import entity.Genre;
import entity.ScreenDimension;
import entity.entityView.report.ConcessionSalesByOperatorView;
import entity.entityView.report.ProductSummaryReportView;
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

    @Autowired
    ProductSummaryReportViewDao productSummaryReportViewDao;


    @RequestMapping(value = "/performance")
    public ModelAndView performance(){
        ModelAndView mav =  new ModelAndView("web-admin/report/performance");
        return mav;
    }
    @RequestMapping(value = "/concession-sale-by-operator")
    public ModelAndView concessionSaleByOperator(){
        ModelAndView mav =  new ModelAndView("web-admin/report/concession-sale-by-operator");
        return mav;
    }
    @RequestMapping(value = "/top-grossing-film")
    public ModelAndView topGrossingFilm(){
        ModelAndView mav =  new ModelAndView("web-admin/report/top-grossing-film");
        return mav;
    }
    @RequestMapping(value = "/sale-by-terminal")
    public ModelAndView saleByTerminal(){
        ModelAndView mav =  new ModelAndView("web-admin/report/sale-by-terminal");
        return mav;
    }
    @RequestMapping(value = "/risk")
    public ModelAndView risk(){
        ModelAndView mav =  new ModelAndView("web-admin/report/risk");
        return mav;
    }
































































    @RequestMapping(value = "/distributor")
    public ModelAndView distributor(){
        ModelAndView mav =  new ModelAndView("web-admin/report/distributor");
        return mav;
    }
    @RequestMapping(value = "/film")
    public ModelAndView film(){
        ModelAndView mav =  new ModelAndView("web-admin/report/film");
        return mav;
    }
    @RequestMapping(value = "/screen")
    public ModelAndView screen(){
        ModelAndView mav =  new ModelAndView("web-admin/report/screen");
        return mav;
    }
    @RequestMapping(value = "/complementary")
    public ModelAndView complementary(){
        ModelAndView mav =  new ModelAndView("web-admin/report/complementary");
        return mav;
    }
    @RequestMapping(value = "/hourly")
    public ModelAndView hourly(){
        ModelAndView mav =  new ModelAndView("web-admin/report/hourly");
        return mav;
    }
    @RequestMapping(value = "/general-rating")
    public ModelAndView generalRating(){
        ModelAndView mav =  new ModelAndView("web-admin/report/general-rating");
        return mav;
    }
    @RequestMapping(value = "/performance-listing")
    public ModelAndView performanceListing(){
        ModelAndView mav =  new ModelAndView("web-admin/report/performance-listing");
        return mav;
    }
    @RequestMapping(value = "/ticket-type")
    public ModelAndView ticketType(){
        ModelAndView mav =  new ModelAndView("web-admin/report/ticket-type");
        return mav;
    }
    @RequestMapping(value = "/sales-transaction")
    public ModelAndView salesTransaction(){
        ModelAndView mav =  new ModelAndView("web-admin/report/sales-transaction");
        return mav;
    }
    @RequestMapping(value = "/transaction-summary")
    public ModelAndView transactionSummary(){
        ModelAndView mav =  new ModelAndView("web-admin/report/transaction-summary");
        return mav;
    }
    @RequestMapping(value = "/ticket-sale-by-operator")
    public ModelAndView ticketSaleByOperator(){
        ModelAndView mav =  new ModelAndView("web-admin/report/ticket-sale-by-operator");
        return mav;
    }
    @RequestMapping(value = "/advance-sale")
    public ModelAndView advanceSale(){
        ModelAndView mav =  new ModelAndView("web-admin/report/advance-sale");
        return mav;
    }
    @RequestMapping(value = "/combo-sale")
    public ModelAndView  comboSale(){
        ModelAndView mav =  new ModelAndView("web-admin/report/combo-sale");
        return mav;
    }
    @RequestMapping(value = "/product-sale")
    public ModelAndView  productSale(){
        ModelAndView mav =  new ModelAndView("web-admin/report/product-sale");
        return mav;
    }
    @RequestMapping(value = "/retails-per-hand")
    public ModelAndView  retailsPerHand(){
        ModelAndView mav =  new ModelAndView("web-admin/report/retails-per-hand");
        return mav;
    }
    @RequestMapping(value = "/product-sale-by-operator")
    public ModelAndView productSellsByOperator(){
        List<ConcessionSalesByOperatorView> concessionSalesByOperatorList=concessionSalesByOperatorViewDao.getAll();
        ModelAndView modelAndView =  new ModelAndView("web-admin/report/product-sale-by-operator");
        modelAndView.addObject("productSalesByOperatorList",concessionSalesByOperatorList);
        return modelAndView;
    }

    @RequestMapping(value = "/product-summary")
    public ModelAndView productSummary(){
        List<ProductSummaryReportView> productSummaryReportViewList=productSummaryReportViewDao.getAll();

        System.out.print(productSummaryReportViewList);
        ModelAndView modelAndView =  new ModelAndView("web-admin/report/product-summary");
        modelAndView.addObject("ProductSummaryReportView",productSummaryReportViewList);
        return modelAndView;
    }








}
