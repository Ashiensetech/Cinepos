package validator.admin.AdminGenreService.editGenre;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


public class EditGenreForm {
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EditGenreForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
