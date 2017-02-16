package validator.admin.AdminSellsService.CreateSells;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import validator.admin.AdminFilmScheduleService.createOrMerge.ScheduleForm;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

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
