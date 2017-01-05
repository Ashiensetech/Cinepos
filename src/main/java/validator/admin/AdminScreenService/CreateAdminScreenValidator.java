package validator.admin.AdminScreenService;

import dao.AuthCredentialDao;
import dao.ScreenDimensionDao;
import dao.UserInfDao;
import dao.UserRoleDao;
import entity.AuthCredential;
import entity.ScreenDimension;
import entity.UserRole;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.admin.AdminUserService.createUser.CreateAdminUserForm;

import java.text.ParseException;

/**
 * Created by mi on 1/4/17.
 */
@Service
public class CreateAdminScreenValidator implements Validator {

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateScreenFrom createScreenFrom = (CreateScreenFrom)obj;

        ScreenDimension  screenDimension = screenDimensionDao.getById(createScreenFrom.getScreenTypeId());

        if(screenDimension==null){
            errors.rejectValue("screenTypeId", "Screen type not found");
        }
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateScreenFrom.class.equals(aClass);
    }
}
