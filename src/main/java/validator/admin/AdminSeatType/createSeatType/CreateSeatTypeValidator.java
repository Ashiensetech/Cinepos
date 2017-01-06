package validator.admin.AdminSeatType.createSeatType;

import dao.SeatTypeDao;
import entity.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sunno on 1/6/17.
 */
@Repository
public class CreateSeatTypeValidator implements Validator {

    @Override
    public void validate(Object obj, Errors errors) {
        CreateSeatTypeForm createSeatTypeForm = (CreateSeatTypeForm) obj;

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateSeatTypeForm.class.equals(aClass);
    }
}
