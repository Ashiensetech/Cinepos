package validator.admin.restservice.genre.editGenre;

import dao.GenreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sunno on 1/6/17.
 */
@Service
public class EditGenreValidator implements Validator {

    @Autowired
    GenreDao genreDao;

    @Override
    public void validate(Object obj, Errors errors) {
        EditGenreForm editGenreForm = (EditGenreForm) obj;

        boolean status  = genreDao.isNameUsedByOther(editGenreForm.getId(),editGenreForm.getName());
        if(status){
            errors.rejectValue("name", "Genre Name already already used by others");
        }

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return EditGenreForm.class.equals(aClass);
    }

}
