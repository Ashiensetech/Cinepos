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
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestMethod;
=======
>>>>>>> 6029d8f626a1fccd249cd2873b1fb23d69d601a4
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.lang.model.element.NestingKind;
import java.util.List;
import java.util.Optional;


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

    @RequestMapping(value = "/transaction-summary-audit")
    public ModelAndView transactionSummaryAudit(){
        ModelAndView mav =  new ModelAndView("web-admin/report/transaction-summary-audit");
        return mav;
    }
    @RequestMapping(value = "/promotion")
    public ModelAndView promotion(){
        ModelAndView mav =  new ModelAndView("web-admin/report/promotion");
        return mav;
    }
    @RequestMapping(value = "/end-of-the-day")
    public ModelAndView endOfTheDay(){
        ModelAndView mav =  new ModelAndView("web-admin/report/end-of-the-day");
        return mav;
    }
    @RequestMapping(value = "/end-of-the-day-operator-break-down")
    public ModelAndView endOfTheDayOperatorBreakDown(){
        ModelAndView mav =  new ModelAndView("web-admin/report/end-of-the-day-operator-break-down");
        return mav;
    }
    @RequestMapping(value = "/automatic-reporting")
    public ModelAndView automaticReporting(){
        ModelAndView mav =  new ModelAndView("web-admin/report/automatic-reporting");
        return mav;
    }
    @RequestMapping(value = "/tender")
    public ModelAndView tender(){
        ModelAndView mav =  new ModelAndView("web-admin/report/tender");
        return mav;
    }
    @RequestMapping(value = "/refund")
    public ModelAndView refund(){
        ModelAndView mav =  new ModelAndView("web-admin/report/refund");
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

    @RequestMapping(value = "/product-summary",method = RequestMethod.GET)
    public ModelAndView productSummary(@RequestParam(value = "startDate",required = false) String startDate,
                                       @RequestParam(value = "endDate",required = false) String endDate
                                       ){

        System.out.print(startDate);
        List<ProductSummaryReportView> productSummaryReportViewList=productSummaryReportViewDao.getAll();

        System.out.print(productSummaryReportViewList);
        ModelAndView modelAndView =  new ModelAndView("web-admin/report/product-summary");
        modelAndView.addObject("ProductSummaryReportView",productSummaryReportViewList);
        return modelAndView;
    }








}
