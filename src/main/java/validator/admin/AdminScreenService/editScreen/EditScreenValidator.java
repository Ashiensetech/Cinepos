package validator.admin.AdminScreenService.editScreen;

import dao.ScreenDimensionDao;
import entity.ScreenDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.admin.AdminScreenService.createScreen.CreateScreenFrom;

/**
 * Created by mi on 1/4/17.
 */
@Service
public class EditScreenValidator implements Validator {

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Override
    public void validate(Object obj, Errors errors) {
        EditScreenFrom editScreenFrom = (EditScreenFrom)obj;

        ScreenDimension  screenDimension = screenDimensionDao.getById(editScreenFrom.getScreenTypeId());

        if(screenDimension==null){
            errors.rejectValue("screenTypeId", "Screen type not found");
        }
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateScreenFrom.class.equals(aClass);
    }
}
