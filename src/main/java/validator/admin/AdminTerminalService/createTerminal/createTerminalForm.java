package validator.admin.AdminTerminalService.createTerminal;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class createTerminalForm {
    @NotBlank(message = "Terminal name are required")
    private String name;

    @NotBlank(message = "IP Address are required")
    private String ipAddress;

    @NotBlank(message = "Terminal type are required")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "createTerminalForm{" +
                "name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
