package validator.admin.restservice.seat.type.createSeatType;

import dao.SeatTypeDao;
import entity.SeatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Service
public class CreateSeatTypeValidator implements Validator {

    @Autowired
    SeatTypeDao seatTypeDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateSeatTypeForm createSeatTypeForm = (CreateSeatTypeForm) obj;

        SeatType seatType  = seatTypeDao.getByName(createSeatTypeForm.getName());
        if(seatType!=null){
            errors.rejectValue("name", "Name already taken");
        }

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateSeatTypeForm.class.equals(aClass);
    }

}
