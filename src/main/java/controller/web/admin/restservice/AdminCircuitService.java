package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.CircuitDao;
import entity.Circuit;
import helper.UtilityHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.restservice.circuit.create.createCircuitForm;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> create(@Valid createCircuitForm createCircuitForm,
                                    BindingResult result,
                                    HttpServletRequest request){
        String errorMsg="";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try{
            ServiceResponse serviceResponse=ServiceResponse.getInstance();
            serviceResponse.bindValidationError(result);

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
                circuit.setBookingCancellationTime(createCircuitForm.getBookingCancellationTime());
                circuit.setRefundCancellationTime(createCircuitForm.getRefundCancellationTime());
                circuit.setRefundDeductionPercentage(createCircuitForm.getRefundDeductionPercentage());
                circuit.setCreatedBy(1);

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
                circuitData.setBookingCancellationTime(createCircuitForm.getBookingCancellationTime());
                circuitData.setRefundCancellationTime(createCircuitForm.getRefundCancellationTime());
                circuitData.setRefundDeductionPercentage(createCircuitForm.getRefundDeductionPercentage());
                circuitData.setUpdatedBy(1);
                circuitData.setUpdatedAt(timestamp);

                circuitDao.update(circuitData);

            }

        }catch (Exception e){

        }
        return ResponseEntity.status(HttpStatus.OK).body(ServiceResponse.getMsg(errorMsg));



    }




}
