package validator.admin.AdminSellsService.CreateSells;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by Sarwar on 2/3/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderForm {
    private Integer terminalId;
    private List<CartForm> cartForms;

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public List<CartForm> getCartForms() {
        return cartForms;
    }

    public void setCartForms(List<CartForm> cartForms) {
        this.cartForms = cartForms;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "terminalId=" + terminalId +
                ", cartForms=" + cartForms +
                '}';
    }
}
