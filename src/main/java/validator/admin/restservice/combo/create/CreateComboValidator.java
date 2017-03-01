package validator.admin.restservice.combo.create;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ConcessionProductDao;
import entity.ConcessionProduct;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sarwar on 1/19/2017.
 */
@Service
public class CreateComboValidator implements Validator {
    @Autowired
    ConcessionProductDao concessionProductDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateComboForm createComboForm = (CreateComboForm) obj;



        try {
            createComboForm.setFormattedStartDate(DateHelper.getStringToDate(createComboForm.getStartDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("startDate", "Start date format miss matched");
        }

        try {
            createComboForm.setFormattedEndDate(DateHelper.getStringToDate(createComboForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End date format miss matched");
        }

        if( createComboForm.getFormattedStartDate()!=null &&  createComboForm.getFormattedEndDate()!=null){
            if(createComboForm.getFormattedStartDate().after(createComboForm.getFormattedEndDate())){
                errors.rejectValue("endDate", "End date is past then start date");
            }
        }


        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Integer>> tRef = new TypeReference<List<Integer>>() {};


        try {

            ComboProductDetailsForm[] myObjects = objectMapper.readValue(createComboForm.getProductIds(), ComboProductDetailsForm[].class);


            if(myObjects==null){
                errors.rejectValue("productIds", "Product can't be empty");
                return;
            }
            createComboForm.setComboProductDetailsForm(new ArrayList<>(Arrays.asList(myObjects)));
        } catch (IOException e) {
            errors.rejectValue("productIds", "Broken json found");
            return;
        }catch (ClassCastException e){
            errors.rejectValue("productIds", "Broken json found while casting to list form array");
            return;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateComboForm.class.equals(aClass);
    }

}
