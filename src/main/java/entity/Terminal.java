package entity;



import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "terminal")
public class Terminal {
    @Basic
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "ip_address")
    private String ipAddress;

    @Basic
    @Column(name = "terminal_code")
    private String terminalCode;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "status")
    private int status;

    @Basic
    @Column(name = "created_by")
    private int createdBy;

    @Basic
    @Column(name = "updated_by")
    private int updatedBy;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at",nullable = true)
    private Timestamp updatedAt;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Terminal terminal = (Terminal) o;

        if (Id != terminal.Id) return false;
        if (status != terminal.status) return false;
        if (createdBy != terminal.createdBy) return false;
        if (updatedBy != terminal.updatedBy) return false;
        if (name != null ? !name.equals(terminal.name) : terminal.name != null) return false;
        if (ipAddress != null ? !ipAddress.equals(terminal.ipAddress) : terminal.ipAddress != null) return false;
        if (terminalCode != null ? !terminalCode.equals(terminal.terminalCode) : terminal.terminalCode != null)
            return false;
        if (type != null ? !type.equals(terminal.type) : terminal.type != null) return false;
        if (createdAt != null ? !createdAt.equals(terminal.createdAt) : terminal.createdAt != null) return false;
        return updatedAt != null ? updatedAt.equals(terminal.updatedAt) : terminal.updatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (terminalCode != null ? terminalCode.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + createdBy;
        result = 31 * result + updatedBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", terminalCode='" + terminalCode + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
