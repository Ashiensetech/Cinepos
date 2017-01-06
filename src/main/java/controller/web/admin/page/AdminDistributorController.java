package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.DistributorDao;
import entity.Distributor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/distributor")
public class AdminDistributorController {
    @Autowired
    DistributorDao distributorDao;
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ModelAndView index(){
         List<Distributor> distributorList=distributorDao.getAll();
         ModelAndView modelAndView=new ModelAndView("web-admin/distributors/all-distributor");
         modelAndView.addObject("distributors",distributorList);
         System.out.print(distributorList);
     return modelAndView;
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ModelAndView createDistributor(){
        ModelAndView modelAndView= new ModelAndView("web-admin/distributors/create-distributor");
        return modelAndView;
    }
    @RequestMapping(value = "/edit/{distributorId}",method = RequestMethod.GET)
   public ModelAndView editDistributor(@PathVariable Integer distributorId){

        Distributor distributor=distributorDao.getById(distributorId);
        if(distributor==null){
            return new ModelAndView("redirect:"+AdminUriPreFix.pageUriPrefix+"/distributor/all");
        }

        ModelAndView modelAndView= new ModelAndView("web-admin/distributors/edit-distributor");
        modelAndView.addObject("distributor",distributor);

       return modelAndView;
   }

}
