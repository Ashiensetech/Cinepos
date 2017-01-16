package validator.admin.AdminConcessionPriceShiftService.createConcessionPriceShift;

import dao.ConcessionPriceShiftDao;
import dao.ConcessionProductDao;
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
public class CreateConcessionPriceShiftValidator implements Validator {
    @Autowired
    ConcessionPriceShiftDao concessionPriceShiftDao;

    @Autowired
    ConcessionProductDao concessionProductDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateConcessionPriceShiftForm concessionPriceShiftForm= (CreateConcessionPriceShiftForm) obj;


        try {
            concessionPriceShiftForm.setFormattedStartDate(DateHelper.getStringToDate(concessionPriceShiftForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            concessionPriceShiftForm.setFormattedEndDate(DateHelper.getStringToDate(concessionPriceShiftForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateConcessionPriceShiftForm.class.equals(aClass);
    }
}
