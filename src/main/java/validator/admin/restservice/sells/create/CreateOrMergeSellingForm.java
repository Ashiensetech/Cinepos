package validator.admin.restservice.sells.create;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mi on 1/24/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrMergeSellingForm {

    @NotBlank(message = "Orders can't be empty")
    String ordersJson;

    public OrderForm orderForm;

    public String getOrdersJson() {
        return ordersJson;
    }

    public void setOrdersJson(String ordersJson) {
        this.ordersJson = ordersJson;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    @Override
    public String toString() {
        return "CreateOrMergeSellingForm{" +
                "orderForm=" + orderForm +
                '}';
    }
}
