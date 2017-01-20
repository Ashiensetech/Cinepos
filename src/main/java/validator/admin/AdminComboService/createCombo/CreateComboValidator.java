package validator.admin.AdminComboService.createCombo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ConcessionProductDao;
import entity.ConcessionProduct;
import entity.Genre;
import helper.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.text.ParseException;
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
            errors.rejectValue("startDate", "Start Date format miss matched");
        }

        try {
            createComboForm.setFormattedEndDate(DateHelper.getStringToDate(createComboForm.getEndDate(), "MM/dd/yyyy"));
        } catch (ParseException e) {
            errors.rejectValue("endDate", "End Date format miss matched");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Integer>> tRef = new TypeReference<List<Integer>>() {};

        try {
            createComboForm.setProductsIdArray(objectMapper.readValue(createComboForm.getProductIds(), tRef));
            List<Integer> comboProductIdList = createComboForm.getProductsIdArray();
            for (Integer comboProductId : comboProductIdList){
                ConcessionProduct concessionProduct = concessionProductDao.getById(comboProductId);
                if(concessionProduct==null){
                    errors.rejectValue("productIds", "Product not found by id :"+comboProductId);
                    break;
                }
            }


        } catch (IOException e) {
            errors.rejectValue("productIds","Broken String found");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateComboForm.class.equals(aClass);
    }

}
