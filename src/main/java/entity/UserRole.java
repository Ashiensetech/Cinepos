package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/3/17.
 */
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "role_name")
    private String roleName;

    @Basic
    @Column(name = "display_name")
    private String displayName;

    @Basic
    @Column(name = "group")
    private String group;

    @Basic
    @Column(name = "created_by")
    private Integer createdBy;


    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (id != userRole.id) return false;
        if (roleName != null ? !roleName.equals(userRole.roleName) : userRole.roleName != null) return false;
        if (displayName != null ? !displayName.equals(userRole.displayName) : userRole.displayName != null)
            return false;
        if (group != null ? !group.equals(userRole.group) : userRole.group != null) return false;
        if (createdBy != null ? !createdBy.equals(userRole.createdBy) : userRole.createdBy != null) return false;
        return !(createdAt != null ? !createdAt.equals(userRole.createdAt) : userRole.createdAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", group='" + group + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
