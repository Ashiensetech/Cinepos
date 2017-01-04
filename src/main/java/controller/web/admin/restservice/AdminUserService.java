package controller.web.admin.restservice;

import controller.web.admin.AdminUriPreFix;
import dao.AuthCredentialDao;
import dao.UserInfDao;
import dao.UserRoleDao;
import entity.AuthCredential;
import entity.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminUserService.CreateAdminUserForm;
import validator.admin.AdminUserService.CreateAdminUserValidator;

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

    //@JsonView(AppCredential.class)
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid CreateAdminUserForm createAdminUserForm,BindingResult result){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.OK).body(serviceResponse.getFormError());
        }

        createAdminUserValidator.validate(createAdminUserForm, result);

        serviceResponse.bindValidationError(result);

        if(serviceResponse.hasErrors()){
            return ResponseEntity.status(HttpStatus.OK).body(serviceResponse.getFormError());
        }
//        userRoleDao.getById();


        UserInf userInf = new UserInf();

        userInf.setEmail(createAdminUserForm.getEmail());

        userInf.setFirstName(createAdminUserForm.getFirstName());
        userInf.setLastName(createAdminUserForm.getLastName());
        userInf.setPhone(createAdminUserForm.getFirstName());
        userInf.setGender(createAdminUserForm.getGender());
        userInf.setAddress(createAdminUserForm.getAddress());
        userInf.setStatus("1");
        userInf.setCreatedBy(1);

        AuthCredential authCredential = new AuthCredential();

        userInfDao.insert(userInf);
        authCredential.setUserName(createAdminUserForm.getUserName());
        authCredential.setPassword(createAdminUserForm.getPassword());
        authCredential.setUserRole(userRoleDao.getById(1));
        authCredential.setIsAdmin(true);
        authCredential.setIsActivated(true);
        authCredential.setChangedDefaultPassword(false);
        authCredential.setUserInf(userInf);
        authCredential.setCreatedBy(1);
        authCredentialDao.insert(authCredential);

        return ResponseEntity.status(HttpStatus.OK).body(authCredentialDao.getById(authCredential.getId()));

    }
}
