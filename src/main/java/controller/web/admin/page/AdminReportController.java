package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.*;
import dao.reportDao.ConcessionSalesByOperatorViewDao;
import dao.reportDao.ProductSummaryReportViewDao;
import entity.*;
import entity.entityView.report.ConcessionSalesByOperatorView;
import entity.entityView.report.ProductSummaryReportView;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.net.www.protocol.http.AuthenticationInfo;

import javax.lang.model.element.NestingKind;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Autowired
    SellsDao sellsDao;

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
    public ModelAndView transactionSummaryAudit(Authentication authentication,
                                                @RequestParam(value = "startDate",required = false) String startDate,
                                                @RequestParam(value = "endDate",required = false) String endDate){
        List<Sells>  sells = new ArrayList<>();
        AuthCredential authCredential =  (AuthCredential)authentication.getPrincipal();

        Timestamp sDate = null;
        Timestamp eDate = null;
        if(startDate!=null && !startDate.trim().equals("")){
            try {
                sDate = DateHelper.getStringToTimeStamp(startDate + " 00:00:00", "yyyy-MM-dd H:m:s");
                System.out.println(sDate);
            } catch (ParseException e) {

            }
        }
        if(endDate!=null && !endDate.trim().equals("")){
            try {
                eDate = DateHelper.getStringToTimeStamp(endDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                System.out.println(eDate);
            } catch (ParseException e) {

            }
        }

        if(sDate!=null && eDate!=null){
            sells = sellsDao.getByDateRange(sDate,eDate);
        }else if(sDate!=null) {
            try {
                Timestamp tmpSData = DateHelper.getStringToTimeStamp(startDate+ " 00:00:00", "yyyy-MM-dd H:m:s");
                Timestamp tmpEData = DateHelper.getStringToTimeStamp(startDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                System.out.println(tmpSData);
                System.out.println(tmpEData);
                sells = sellsDao.getByDateRange(tmpSData, tmpEData);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            sells = sellsDao.getAll();
        }


        ModelAndView mav =  new ModelAndView("web-admin/report/transaction-summary-audit");
        mav.addObject("sells",sells);
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
    public ModelAndView productSummary(
                                        Authentication authentication,
                                        @RequestParam(value = "startDate",required = false) String startDate,
                                       @RequestParam(value = "endDate",required = false) String endDate
                                       ){
        AuthCredential authCredential =  (AuthCredential)authentication.getPrincipal();


        Timestamp sDate = null;
        Timestamp eDate = null;

        Date fDate=null;
        if(startDate!=null && !startDate.trim().equals("")){
            try {
                sDate = DateHelper.getStringToTimeStamp(startDate + " 00:00:00", "yyyy-MM-dd H:m:s");
                System.out.println(sDate);
            } catch (ParseException e) {

            }
        }
        if(endDate!=null && !endDate.trim().equals("")){
            try {
                eDate = DateHelper.getStringToTimeStamp(endDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                System.out.println(eDate);
            } catch (ParseException e) {

            }
        }


        List<ProductSummaryReportView> productSummaryReportViewList = new ArrayList<>();

        System.out.println(sDate);

        if(sDate!=null && eDate!=null){
            productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(sDate, eDate);
        }else if(sDate!=null) {
            System.out.println(productSummaryReportViewList);
            fDate=sDate;
            sDate=null;
            productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(fDate, fDate);
            try {
                Timestamp tmpSData = DateHelper.getStringToTimeStamp(startDate+ " 00:00:00", "yyyy-MM-dd H:m:s");
                Timestamp tmpEData = DateHelper.getStringToTimeStamp(startDate + " 23:59:59", "yyyy-MM-dd H:m:s");
                System.out.println(tmpSData);
                System.out.println(tmpEData);
                productSummaryReportViewList = productSummaryReportViewDao.getByDateRange(tmpSData, tmpEData);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else{
            productSummaryReportViewList = productSummaryReportViewDao.getAll();
        }

        Calendar calendar = new GregorianCalendar();

        int year       = calendar.get(Calendar.YEAR);
        int month      = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        String printingDate=year+"-"+month+"-"+dayOfMonth;
        int printedTime  = calendar.get(Calendar.HOUR_OF_DAY);

        ModelAndView modelAndView =  new ModelAndView("web-admin/report/product-summary");
        modelAndView.addObject("ProductSummaryReportView",productSummaryReportViewList);
        modelAndView.addObject("startDate",sDate);
        modelAndView.addObject("endDate",eDate);
        modelAndView.addObject("fDate",fDate);
        modelAndView.addObject("printed_by",authCredential.getUserName());
        modelAndView.addObject("printingDate",printingDate);
        modelAndView.addObject("printedTime",printedTime);
        return modelAndView;
    }
}
