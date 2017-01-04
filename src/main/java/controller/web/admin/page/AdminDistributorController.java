package controller.web.admin.page;

import dao.DistributorDao;
import entity.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "distributor")
public class AdminDistributorController {
    @Autowired
    DistributorDao distributorDao;
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public ModelAndView index(){
         List<Distributor> distributorList=distributorDao.getAll();
         ModelAndView modelAndView=new ModelAndView("web-admin/distributors/index");
         modelAndView.addObject("distributor",distributorList);
         System.out.print(distributorList);
     return modelAndView;
    }
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView modelAndView= new ModelAndView("web-admin/distributors/create");
        return modelAndView;
    }

}
