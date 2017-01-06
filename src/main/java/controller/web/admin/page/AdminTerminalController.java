package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.TerminalDao;
import entity.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/terminal")
public class AdminTerminalController {
    @Autowired
    TerminalDao terminalDao;
    @RequestMapping(value = "all",method = RequestMethod.GET)
    public  ModelAndView index(){
        List<Terminal> terminalList=terminalDao.getAll();
        ModelAndView modelAndView=new ModelAndView("web-admin/terminals/all-terminal");
        modelAndView.addObject("terminalList",terminalList);
        return modelAndView;
    }
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView modelAndView=new ModelAndView("web-admin/terminals/create-terminal");
        return modelAndView;
    }


}
