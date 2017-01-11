package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.CircuitDao;
import entity.Circuit;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sarwar on 1/10/2017.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/circuit")
public class AdminCircuitController {
    @Autowired
    CircuitDao circuitDao;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
        Circuit circuit=circuitDao.getCircuit();
        ModelAndView modelAndView=new ModelAndView("web-admin/circuit/index-circuit");
        modelAndView.addObject("circuit",circuit);
        return modelAndView;
    }
}
