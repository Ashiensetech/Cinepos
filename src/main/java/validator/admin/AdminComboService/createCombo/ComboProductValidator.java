package validator.admin.AdminComboService.createCombo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ComboProductValidator  implements  Validator {
    @Override
    public void validate(Object obj, Errors errors) {
        ComboProductForm comboProductForm = (ComboProductForm) obj;
        ObjectMapper objectMapper = new ObjectMapper();
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return ComboProductForm.class.equals(aClass);
    }

}
