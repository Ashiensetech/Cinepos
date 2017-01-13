package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.ConcessionProductCategoryDao;
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
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/product-category")
public class AdminProductCategoryController {
    @Autowired
    ConcessionProductCategoryDao concessionProductCategoryDao;



    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView index(){
        List<ConcessionProductCategory> concessionProductCategoryList=concessionProductCategoryDao.getAll();
        ModelAndView modelAndView=new ModelAndView("web-admin/product-category/all-product-category");
        modelAndView.addObject("concessionProductCategory",concessionProductCategoryList);
        System.out.print(concessionProductCategoryList);
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createProductCategory(){
        ModelAndView modelAndView= new ModelAndView("web-admin/product-category/create-product-category");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{productcategoryId}",method = RequestMethod.GET)
    public ModelAndView editProductCategory(@PathVariable Integer productcategoryId){

        ConcessionProductCategory concessionProductCategory=concessionProductCategoryDao.getById(productcategoryId);
        if(concessionProductCategory==null){
            return new ModelAndView("redirect:"+AdminUriPreFix.pageUriPrefix+"/product-category/all");
        }

        ModelAndView modelAndView= new ModelAndView("web-admin/product-category/edit-product-category");
        modelAndView.addObject("concessionProductCategory",concessionProductCategory);

        return modelAndView;
    }

}
