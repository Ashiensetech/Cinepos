package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.ScreenDao;
import dao.ScreenDimensionDao;
import entity.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminScreenService.CreateAdminScreenValidator;
import validator.admin.AdminScreenService.CreateScreenFrom;

import javax.validation.Valid;

/**
 * Created by mi on 1/5/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/screen")
public class AdminScreenService {
    @Autowired
    ScreenDao screenDao;

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    CreateAdminScreenValidator createAdminScreenValidator;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid CreateScreenFrom createScreenFrom,BindingResult result){
        System.out.println(createScreenFrom);
        ServiceResponse serviceResponse = ServiceResponse.getInstance();

        /***************** Validation Layer [Start] *************/

        /**
        * Basic form validation
        * */
        serviceResponse.bindValidationError(result);
        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        /**
         * Business logic validation
         * */
        createAdminScreenValidator.validate(createScreenFrom,result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
        /***************** Validation Layer [End] *************/

        /***************** Service Layer [Start] *************/
        Screen screen = new Screen();

        screen.setName(createScreenFrom.getName());
        screen.setNoOfSeat(createScreenFrom.getSeatCount());
        screen.setRowCount(createScreenFrom.getRowCount());
        screen.setColumnCount(createScreenFrom.getColumnCount());
        screen.setClosingTime(createScreenFrom.getClosingTime());
        screen.setOpeningTime(createScreenFrom.getOpeningTime());
        screen.setScreenDimension(screenDimensionDao.getById(createScreenFrom.getScreenTypeId()));
        screen.setActive(true);


        screenDao.insert(screen);
        /***************** Service Layer [Ends] *************/



        return ResponseEntity.status(HttpStatus.OK).body(screen);

    }
}
