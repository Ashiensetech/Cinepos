package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.ScreenDao;
import dao.ScreenDimensionDao;
import dao.SeatTypeDao;
import entity.Screen;
import entity.ScreenSeat;
import entity.SeatType;
import helper.ScreenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/screen")
public class AdminScreenController {
    @Autowired
    private ScreenDimensionDao screenDimensionDao;

    @Autowired
    private ScreenDao screenDao;

    @Autowired
    private SeatTypeDao seatTypeDao;
    @RequestMapping(value = "/all")
    public ModelAndView allScreenPage(){
        ModelAndView mav =  new ModelAndView("web-admin/screen/all-screen");
        mav.addObject("screens",screenDao.getAll());
        return mav;
    }


    @RequestMapping(value = "/create")
    public ModelAndView createScreenPage(){
      ModelAndView mav =  new ModelAndView("web-admin/screen/create-screen");
        mav.addObject("screenDimensions",screenDimensionDao.getAll());
     return mav;
    }
    @RequestMapping(value = "/edit/{screenId}")
    public ModelAndView editScreenPage(@PathVariable Integer screenId){
        ModelAndView mav =  new ModelAndView("web-admin/screen/edit-screen");
        Screen screen = screenDao.getById(screenId);
        if(screen==null){
            return new ModelAndView("redirect:"+AdminUriPreFix.pageUriPrefix+"/screen/all");
        }
        mav.addObject("screenDimensions",screenDimensionDao.getAll());
        mav.addObject("screen",screen);
        return mav;
    }


    @RequestMapping(value = "/seat/create-edit/{screenId}")
    public ModelAndView createScreenSeatPage(@PathVariable Integer screenId){
        ModelAndView mav =  new ModelAndView("web-admin/screen/create-screen-seat");
        Screen screen = screenDao.getById(screenId);
        List<SeatType> seatTypes = seatTypeDao.getAll();
        SeatType seatType = seatTypeDao.getDefaultSeatType();
        List<List<ScreenSeat>> screenSeatList = new ArrayList<>();
        if(!screen.getIsSeatPlanComplete()){
            screenSeatList = ScreenHelper.generateSeats(screen.getRowCount(),screen.getColumnCount(),seatType);
        }else{
            screenSeatList = ScreenHelper.singleDimensionToTwoDimensionList(screen.getSeats(),screen.getRowCount(),screen.getColumnCount());
        }

        mav.addObject("screen",screen);
        mav.addObject("seatTypes",seatTypes);
        mav.addObject("screenSeatList",screenSeatList);
        return mav;
    }


}
