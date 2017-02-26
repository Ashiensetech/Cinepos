package controller.web.admin.restservice;

import com.fasterxml.jackson.annotation.JsonView;
import controller.web.admin.AdminUriPreFix;

import dao.AuthCredentialDao;
import dao.UserInfDao;
import dao.UserRoleDao;

import entity.AuthCredential;
import entity.UserInf;

import entity.iface.AppCredential;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import utility.ServiceResponse;

import validator.admin.restservice.user.create.CreateAdminUserForm;
import validator.admin.restservice.user.create.CreateAdminUserValidator;
import validator.admin.restservice.user.edit.EditAdminUserForm;
import validator.admin.restservice.user.edit.EditAdminUserValidator;

import javax.validation.Valid;

/**
 * Created by mi on 1/3/17.
 */
@RestController
@RequestMapping(AdminUriPreFix.apiUriPrefix +"/user")
public class AdminUserService {
    @Autowired
    UserInfDao userInfDao;

    @Autowired
    AuthCredentialDao authCredentialDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    CreateAdminUserValidator createAdminUserValidator;

    @Autowired
    EditAdminUserValidator editAdminUserValidator;

    @JsonView(AppCredential.class)
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid CreateAdminUserForm createAdminUserForm,BindingResult result){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        createAdminUserValidator.validate(createAdminUserForm, result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }
//        userRoleDao.getById();


        UserInf userInf = new UserInf();

        userInf.setEmail(createAdminUserForm.getEmail());

        userInf.setFirstName(createAdminUserForm.getFirstName());
        userInf.setLastName(createAdminUserForm.getLastName());
        userInf.setPhone(createAdminUserForm.getPhone());
        userInf.setGender(createAdminUserForm.getGender());
        userInf.setAddress(createAdminUserForm.getAddress());
        userInf.setDob(createAdminUserForm.getDobDate());
        userInf.setStatus("1");
        userInf.setCreatedBy(1);

        AuthCredential authCredential = new AuthCredential();

        userInfDao.insert(userInf);
        authCredential.setUserName(createAdminUserForm.getUserName());
        authCredential.setPassword(createAdminUserForm.getPassword());
        authCredential.setUserRole(userRoleDao.getById(createAdminUserForm.getRoleId()));
        authCredential.setIsAdmin(true);
        authCredential.setIsActivated(true);
        authCredential.setChangedDefaultPassword(false);
        authCredential.setUserInf(userInf);
        authCredential.setCreatedBy(1);
        authCredentialDao.insert(authCredential);

        return ResponseEntity.status(HttpStatus.OK).body(authCredentialDao.getById(authCredential.getId()));

    }
    @RequestMapping(value = "/edit/{authCredentialId}",method = RequestMethod.POST)
    public ResponseEntity<?> editUser(@Valid EditAdminUserForm editAdminUserForm,
                                      BindingResult result,
                                      @PathVariable Integer authCredentialId){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        serviceResponse.bindValidationError(result);
        /**
         * Get AuthCredential by id for edit
         * */

        System.out.println(editAdminUserForm);
        AuthCredential authCredential = authCredentialDao.getById(authCredentialId);

        if(authCredential == null){
            serviceResponse.setValidationError("authCredentialId","No user information found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        editAdminUserForm.setId(authCredentialId);


        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }

        editAdminUserValidator.validate(editAdminUserForm, result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse.getFormError());
        }



        /**
         * Get UserInf from auth credential
         * */

        UserInf userInf = authCredential.getUserInf();

        if(userInf == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ServiceResponse.getMsg("User inf missing"));
        }

        userInf.setEmail(editAdminUserForm.getEmail());

        userInf.setFirstName(editAdminUserForm.getFirstName());
        userInf.setLastName(editAdminUserForm.getLastName());
        userInf.setPhone(editAdminUserForm.getPhone());
        userInf.setGender(editAdminUserForm.getGender());
        userInf.setAddress(editAdminUserForm.getAddress());
        userInf.setStatus("1");
        userInf.setCreatedBy(1);
        userInf.setDob(editAdminUserForm.getDobDate());


        //userInfDao.insert(userInf);
        authCredential.setUserName(editAdminUserForm.getUserName());
        authCredential.setUserRole(userRoleDao.getById(editAdminUserForm.getRoleId()));
        authCredential.setIsAdmin(true);
        authCredential.setIsActivated(true);
        authCredential.setChangedDefaultPassword(false);
        authCredential.setUserInf(userInf);
        authCredential.setCreatedBy(1);
        authCredentialDao.update(authCredential);

        return ResponseEntity.status(HttpStatus.OK).body(authCredentialDao.getById(authCredential.getId()));

    }
    @RequestMapping(value = "/active-inactive/{authCredentialId}/{activationType}",method = RequestMethod.POST)
    public ResponseEntity<?> statusUpdate( @PathVariable Integer authCredentialId,
                                      @PathVariable String activationType){

        System.out.println(activationType);

        boolean statusType;

        if(activationType.equals("activate")){
            statusType = true;
        }else  if(activationType.equals("deactivate")){
            statusType = false;
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ServiceResponse.getMsg("Uri segment wrong"));
        }

        AuthCredential authCredential = authCredentialDao.getById(authCredentialId);

        if(authCredential == null){
            ServiceResponse serviceResponse = ServiceResponse.getInstance();
            serviceResponse.setValidationError("authCredentialId","No User information found");

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(serviceResponse);
        }

        authCredential.setIsActivated(statusType);
        authCredentialDao.update(authCredential);
        return ResponseEntity.status(HttpStatus.OK).body(authCredential);
    }
}

