package controller.web.admin.page;

import dao.reportDao.ProductSaleReportViewDao;
import entity.entityReport.ProductSummaryReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by mi on 1/2/17.
 */
@Controller
@RequestMapping("/admin")
public class Test {
    @Autowired
    ProductSaleReportViewDao productSaleReportViewDao;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init(){
        return new ModelAndView("web-admin/test");
    }

    @RequestMapping(value = "/data-tester", method = RequestMethod.GET)
    public ModelAndView QueryTesting()
    {
        List<ProductSummaryReportView> productSaleReportView= productSaleReportViewDao.getAll();
        System.out.println(productSaleReportView);
        return new ModelAndView("web-admin/test");
    }




}
