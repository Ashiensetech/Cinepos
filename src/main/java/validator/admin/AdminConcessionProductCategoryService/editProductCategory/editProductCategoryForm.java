package validator.admin.AdminConcessionProductCategoryService.editProductCategory;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Sarwar on 1/13/2017.
 */
public class editProductCategoryForm {

    @NotBlank(message = "Distributor name are required")
    @Length(max = 30,message = "Distributor name too large")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "editProductCategoryForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
