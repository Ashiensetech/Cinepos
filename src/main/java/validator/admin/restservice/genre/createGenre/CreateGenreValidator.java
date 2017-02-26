package validator.admin.restservice.genre.createGenre;

import dao.GenreDao;
import entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by sunno on 1/6/17.
 */
@Service
public class CreateGenreValidator implements Validator {

    @Autowired
    GenreDao genreDao;

    @Override
    public void validate(Object obj, Errors errors) {
        CreateGenreForm createGenreForm = (CreateGenreForm) obj;

        Genre genre  = genreDao.getByName(createGenreForm.getName());
        if(genre!=null){
            errors.rejectValue("name", "Genre Name already exist");
        }

    }
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateGenreForm.class.equals(aClass);
    }

}
