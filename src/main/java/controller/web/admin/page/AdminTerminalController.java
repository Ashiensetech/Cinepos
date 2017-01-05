package controller.web.admin.page;

import dao.TerminalDao;
import entity.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "terminal")
public class AdminTerminalController {
    @Autowired
    TerminalDao terminalDao;
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public  ModelAndView index(){
        List<Terminal> terminalList=terminalDao.getAll();
        System.out.println(terminalList);
        System.out.println("kjkjkjk");
        ModelAndView modelAndView=new ModelAndView("web-admin/terminals/all-terminal");
        return modelAndView;
    }
    @RequestMapping(value = "create",method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView modelAndView=new ModelAndView("web-admin/terminals/create-terminal");
        return modelAndView;
    }


}
