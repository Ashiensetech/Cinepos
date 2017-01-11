package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.VatSettingDao;
import entity.VatSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminVatSettingService.createVatSetting.createVatSettingForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/11/2017.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/vat-setting")
public class AdminVatSettingService {
    @Autowired
    VatSettingDao vatSettingDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid createVatSettingForm createVatSettingForm, BindingResult result,
                                    HttpServletRequest request){
        String errorMsg="";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try{
            ServiceResponse serviceResponse= ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

            if(serviceResponse.hasErrors()){
                return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
            }
            VatSetting vatSetting=new VatSetting();


            if(createVatSettingForm.getId()==null){
                errorMsg="Circuit successfully created";
                vatSetting.setName(createVatSettingForm.getName());
                vatSetting.setAmount(createVatSettingForm.getAmount());
                vatSetting.setDescription(createVatSettingForm.getDescription());
                vatSetting.setCreatedBy(1);

                vatSettingDao.create(vatSetting);

            }else{

                errorMsg="Circuit successfully updated";
                VatSetting vatSettingData = vatSettingDao.getById(createVatSettingForm.getId());
                vatSettingData.setName(createVatSettingForm.getName());
                vatSettingData.setAmount(createVatSettingForm.getAmount());
                vatSettingData.setDescription(createVatSettingForm.getDescription());

                vatSettingDao.update(vatSettingData);

            }

        }catch (Exception e){

        }
        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));



    }


}
