package controller.web.admin.restservice;

import dao.AuthCredentialDao;
import dao.UserInfDao;
import dao.UserRoleDao;
import entity.AuthCredential;
import entity.UserInf;
import entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utility.ServiceResponse;
import validator.admin.AdminUserService.CreateAdminUserForm;

import javax.validation.Valid;

/**
 * Created by mi on 1/3/17.
 */
@RestController
@RequestMapping(AdminApiPreFix.authUriPrefix+"/user")
public class AdminUserService {
    @Autowired
    UserInfDao userInfDao;

    @Autowired
    AuthCredentialDao authCredentialDao;

    @Autowired
    UserRoleDao userRoleDao;

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public ResponseEntity<?> create(@Valid CreateAdminUserForm createAdminUserForm,BindingResult result){
        ServiceResponse serviceResponse = ServiceResponse.getInstance();
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
        authCredential.setChangedDefultPassword(false);
        authCredential.setUserInf(userInf);
        authCredentialDao.insert(authCredential);

        return ResponseEntity.status(HttpStatus.OK).body(authCredential);

    }
}
