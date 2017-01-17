package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionProductCategoryDao;
import dao.ConcessionProductDao;
import entity.Circuit;
import entity.ConcessionProduct;
import entity.ConcessionProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Sarwar on 1/13/2017.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/concession-product")
public class AdminConcessionProductController {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;
    @Autowired
    ConcessionProductDao concessionProductDao;

    @RequestMapping(value = "all",method = RequestMethod.GET)
    public ModelAndView index(){

        List<ConcessionProduct> concessionProductList=concessionProductDao.getAll();
        System.out.println(concessionProductList);
        ModelAndView modelAndView=new ModelAndView("web-admin/concession-product/all-concession-product");
        modelAndView.addObject("concessionProductList",concessionProductList);
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createDistributor(){
        List<ConcessionProductCategory> productCategoryList=concessionProductCategoryDao.getAll();
        ModelAndView modelAndView= new ModelAndView("web-admin/concession-product/create-concession-product");
        modelAndView.addObject("ProductCategoryList",productCategoryList);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{productId}",method = RequestMethod.GET)
    public ModelAndView editDistributor(@PathVariable Integer productId){
        List<ConcessionProductCategory> productCategoryList=concessionProductCategoryDao.getAll();
        ConcessionProduct concessionProduct=concessionProductDao.getById(productId);
        ModelAndView modelAndView= new ModelAndView("web-admin/concession-product/edit-concession-product");
        System.out.println(concessionProduct);
        System.out.println(concessionProduct.getConcessionProductImages());
        modelAndView.addObject("ProductCategoryList",productCategoryList);
        modelAndView.addObject("concessionProduct",concessionProduct);
        return modelAndView;
    }
}
