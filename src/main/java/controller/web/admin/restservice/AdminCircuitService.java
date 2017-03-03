package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.CircuitDao;
import entity.AuthCredential;
import entity.Circuit;
import helper.DateHelper;
import helper.UtilityHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.restservice.circuit.create.CreateCircuitForm;

import javax.validation.Valid;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/10/2017.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/circuit")
public class AdminCircuitService {
    @Autowired
    CircuitDao circuitDao;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> create(Authentication authentication,
                                    @Valid CreateCircuitForm createCircuitForm,
                                    BindingResult result){
        AuthCredential authCredential = (AuthCredential)authentication.getPrincipal();
        String errorMsg="";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ServiceResponse serviceResponse=ServiceResponse.getInstance();
        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }


        try {
            createCircuitForm.setBcTime(DateHelper.getStringToTime(createCircuitForm.getBookingCancellationTime()));
           if(createCircuitForm.getBcTime()==null){
               serviceResponse.setValidationError("bookingCancellationTime", "Invalid time format");
           }

        }catch (IllegalArgumentException e){
            serviceResponse.setValidationError("bookingCancellationTime", "Invalid time format");
        }


        try {
            createCircuitForm.setRcTime(DateHelper.getStringToTime(createCircuitForm.getRefundCancellationTime()));
            if(createCircuitForm.getRcTime()==null){
                serviceResponse.setValidationError("refundCancellationTime","Invalid time format");
            }
        }catch (IllegalArgumentException e){
            serviceResponse.setValidationError("refundCancellationTime","Invalid time format");
        }
        if(serviceResponse.hasErrors()){
            return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        Circuit circuit=new Circuit();
        UtilityHelper utHlpObj=new UtilityHelper();
        System.out.println(createCircuitForm.getId());

        if(createCircuitForm.getId()==null){
            errorMsg="Circuit successfully created";

            circuit.setSiteName(createCircuitForm.getSiteName());
            circuit.setAddress(createCircuitForm.getAddress());
            circuit.setCity(createCircuitForm.getCity());
            circuit.setCountry(createCircuitForm.getCountry());
            circuit.setWebSite(createCircuitForm.getWebSite());
            circuit.setPhoneNo(createCircuitForm.getPhoneNo());
            circuit.setScreenNo(createCircuitForm.getScreenNo());
            circuit.setBookingCancellationTime(createCircuitForm.getBcTime());
            circuit.setRefundCancellationTime(createCircuitForm.getRcTime());
            circuit.setRefundDeductionPercentage(createCircuitForm.getRefundDeductionPercentage());
            circuit.setCreatedBy(authCredential.getId());

            int circuitId=circuitDao.insert(circuit);

            String randStr=utHlpObj.getRandomStr(5);
            String circuitIdPad= StringUtils.leftPad(Integer.toString(circuitId), 3, "0");
            String circuitCode=randStr+circuitIdPad;
            Circuit circuitData=circuitDao.getById(circuitId);

            circuitData.setId(circuitId);
            circuitData.setSiteCode(circuitCode);
            circuitDao.update(circuitData);


        }else{

            errorMsg="Circuit successfully updated";

            Circuit circuitData=circuitDao.getById(createCircuitForm.getId());
            circuitData.setSiteName(createCircuitForm.getSiteName());
            circuitData.setAddress(createCircuitForm.getAddress());
            circuitData.setCity(createCircuitForm.getCity());
            circuitData.setCountry(createCircuitForm.getCountry());
            circuitData.setWebSite(createCircuitForm.getWebSite());
            circuitData.setPhoneNo(createCircuitForm.getPhoneNo());
            circuitData.setScreenNo(createCircuitForm.getScreenNo());
            circuitData.setBookingCancellationTime(createCircuitForm.getBcTime());
            circuitData.setRefundCancellationTime(createCircuitForm.getRcTime());
            circuitData.setRefundDeductionPercentage(createCircuitForm.getRefundDeductionPercentage());
            circuitData.setUpdatedBy(authCredential.getId());
            circuitData.setUpdatedAt(timestamp);

            circuitDao.update(circuitData);

        }
        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));



    }




}
