package validator.admin.restservice.concession.product.category.create;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Sarwar on 1/13/2017.
 */
public class createProductCategoryForm {

    @NotBlank(message = "Product Category are required")
    @Length(max = 30,message = "Product Category name too large")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "createProductCategoryForm{" +
                "name='" + name + '\'' +
                '}';
    }
}
