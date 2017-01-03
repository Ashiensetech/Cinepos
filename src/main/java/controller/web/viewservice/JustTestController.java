package controller.web.viewservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class JustTestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView getData(){
         List<String> list=getList();
         //ModelAndView model=new ModelAndView("index");
         ModelAndView model=new ModelAndView("web-admin/layouts/header");

        model.addObject("lists",list);

         return model;

    }

    private List<String> getList(){

        List<String> list=new ArrayList<String>();
        list.add("List A");
        list.add("List B");
        list.add("List C");
        list.add("List D");
        list.add("List E");
        list.add("List F");

        return list;

    }


}
