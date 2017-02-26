package validator.admin.restservice.seat.priceshift.createSeatPriceShift;

import dao.SeatPriceShiftDao;
import dao.SeatTypeDao;
import entity.SeatPriceShift;
import entity.SeatType;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;

/**
 * Created by sunno on 1/16/17.
 */
@Service
public class CreateSeatPriceShiftValidator implements Validator {
    @Autowired
    SeatPriceShiftDao seatPriceShiftDao;

    @Autowired
    SeatTypeDao seatTypeDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateSeatPriceShiftForm seatPriceShiftForm = (CreateSeatPriceShiftForm) obj;

        try {
            seatPriceShiftForm.setFormattedStartDate(DateHelper.getStringToDate(seatPriceShiftForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            seatPriceShiftForm.setFormattedEndDate(DateHelper.getStringToDate(seatPriceShiftForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }

        if (seatPriceShiftForm.getFormattedEndDate().before(seatPriceShiftForm.getFormattedStartDate())) {
            errors.rejectValue("endDate", "End Date should be after start date");
        }
        SeatPriceShift seatPriceShift = seatPriceShiftDao.getBySeatAndDates(seatPriceShiftForm.getSeatTypeId(),seatPriceShiftForm.getFormattedStartDate(),seatPriceShiftForm.getFormattedEndDate());
        if(seatPriceShift!=null){
            errors.rejectValue("startDate", "Start date or end date already exist");
        }

        SeatType seatType= seatTypeDao.getById(seatPriceShiftForm.getSeatTypeId());
        if(seatType==null){
            errors.rejectValue("seatTypeId", "Seat type does not exists");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateSeatPriceShiftForm.class.equals(aClass);
    }
}
