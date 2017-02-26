package validator.admin.restservice.user.edit;

import dao.AuthCredentialDao;
import dao.UserInfDao;
import dao.UserRoleDao;
import entity.UserRole;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;

/**
 * Created by mi on 1/4/17.
 */
@Service
public class EditAdminUserValidator implements Validator {
    @Autowired
    AuthCredentialDao authCredentialDao;

    @Autowired
    UserInfDao userInfDao;

    @Autowired
    UserRoleDao userRoleDao;



    @Override
    public void validate(Object obj, Errors errors) {
        EditAdminUserForm editAdminUserForm = (EditAdminUserForm)obj;


        UserRole userRole = userRoleDao.getById(editAdminUserForm.getRoleId());
        if(userRole==null) {
            errors.rejectValue("roleId", "No role found");
        }

        boolean  userExistStatus = authCredentialDao.isUserNameUsedByOthers(editAdminUserForm.getId(),editAdminUserForm.getUserName());
        boolean  emailExistStatus = authCredentialDao.isEmailUsedByOthers(editAdminUserForm.getId(), editAdminUserForm.getEmail());

        if(userExistStatus){
            errors.rejectValue("userName", "User name already taken by other user");
        }
        if(emailExistStatus){
            errors.rejectValue("email", "Email name already been used by other use");
        }

        try {
            editAdminUserForm.setDobDate(DateHelper.getStringToDate(editAdminUserForm.getDob(), "mm/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("dob", "Date of birth format miss matched");
        }


    }
    @Override
    public boolean supports(Class<?> aClass) {
        return EditAdminUserForm.class.equals(aClass);
    }
}
