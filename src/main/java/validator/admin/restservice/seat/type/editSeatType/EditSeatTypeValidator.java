package validator.admin.restservice.seat.type.editSeatType;

import dao.SeatTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import validator.admin.restservice.seat.type.createSeatType.CreateSeatTypeForm;

/**
 * Created by sunno on 1/6/17.
 */
@Repository
public class EditSeatTypeValidator implements Validator {

    @Autowired
    SeatTypeDao seatTypeDao;

    @Override
    public void validate(Object obj, Errors errors) {
        EditSeatTypeForm editSeatTypeForm = (EditSeatTypeForm) obj;

        boolean nameUsedByOthers  = seatTypeDao.isNameUsedByOthers(editSeatTypeForm.getId(), editSeatTypeForm.getName());
        if(nameUsedByOthers){
            errors.rejectValue("name", "Name already taken");
        }

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateSeatTypeForm.class.equals(aClass);
    }

}
