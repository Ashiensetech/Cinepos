package validator.admin.AdminScreenService.createScreen;

import dao.ScreenDimensionDao;
import entity.ScreenDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by mi on 1/4/17.
 */
@Service
public class CreateScreenValidator implements Validator {

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateScreenFrom createScreenFrom = (CreateScreenFrom)obj;

        ScreenDimension  screenDimension = screenDimensionDao.getById(createScreenFrom.getScreenTypeId());

        if(screenDimension==null){
            errors.rejectValue("screenTypeId", "Screen type not found");
        }
        if(createScreenFrom.getSeatCount()<=0){
            errors.rejectValue("seatCount", "Must be greater then zero");
        }

        if(createScreenFrom.getRowCount()<=0){
            errors.rejectValue("rowCount", "Must be greater then zero");
        }

        if(createScreenFrom.getColumnCount() <=0){
            errors.rejectValue("columnCount", "Must be greater then zero");
        }
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateScreenFrom.class.equals(aClass);
    }
}
