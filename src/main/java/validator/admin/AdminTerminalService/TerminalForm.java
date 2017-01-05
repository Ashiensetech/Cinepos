package validator.admin.AdminTerminalService;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class TerminalForm {
    @NotBlank(message = "Terminal name are required")
    private String name;

    @NotBlank(message = "IP Address are required")
    private String ipAddress;

    @NotBlank @Min(value = 5,message = "Terminal type are required")
    private String type;

    @Override
    public String toString() {
        return "TerminalForm{" +
                "name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
