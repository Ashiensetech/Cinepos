package controller.web.admin.page;

import controller.web.admin.AdminUriPreFix;
import dao.VatSettingDao;
import entity.Circuit;
import entity.VatSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sarwar on 1/11/2017.
 */
@Controller
@RequestMapping(AdminUriPreFix.pageUriPrefix+"/vat-setting")
public class AdminVatSettingController {
    @Autowired
    VatSettingDao vatSettingDao;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
        VatSetting vatSetting=vatSettingDao.getVatSetting();
        ModelAndView modelAndView=new ModelAndView("web-admin/vat-setting/index-vat-setting");
        modelAndView.addObject("vatSetting",vatSetting);
        return modelAndView;
    }

}
