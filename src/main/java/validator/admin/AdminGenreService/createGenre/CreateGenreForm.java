package validator.admin.AdminGenreService.createGenre;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CreateGenreForm {
    @NotNull(message = "Name required")
    @NotBlank(message = "Name required")
    @Length(max=50,message = "Name too large")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateGenreForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
