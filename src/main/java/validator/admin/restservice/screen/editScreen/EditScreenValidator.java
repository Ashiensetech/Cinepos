package validator.admin.restservice.screen.editScreen;

import dao.ScreenDao;
import dao.ScreenDimensionDao;
import entity.Screen;
import entity.ScreenDimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.admin.restservice.screen.createScreen.CreateScreenFrom;

/**
 * Created by mi on 1/4/17.
 */
@Service
public class EditScreenValidator implements Validator {

    @Autowired
    ScreenDimensionDao screenDimensionDao;

    @Autowired
    ScreenDao screenDao;

    @Override
    public void validate(Object obj, Errors errors) {
        EditScreenFrom editScreenFrom = (EditScreenFrom)obj;

        ScreenDimension  screenDimension = screenDimensionDao.getById(editScreenFrom.getScreenTypeId());

        if(screenDimension==null){
            errors.rejectValue("screenTypeId", "Screen type not found");
        }

        Screen screen =screenDao.getById(editScreenFrom.getId());

        if(screen.getIsSeatPlanComplete() && screen.getSeats()!=null && screen.getSeats().size()>0){
            if(screen.getRowCount() != editScreenFrom.getRowCount()){
                errors.rejectValue("rowCount", "Row can't be changed after seat plan is created");
            }
            if(screen.getColumnCount() != editScreenFrom.getColumnCount()){
                errors.rejectValue("columnCount", "Column can't be changed after seat plan is created");
            }
        }
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateScreenFrom.class.equals(aClass);
    }
}
