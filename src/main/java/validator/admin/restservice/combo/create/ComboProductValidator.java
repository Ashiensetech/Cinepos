package validator.admin.restservice.combo.create;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
