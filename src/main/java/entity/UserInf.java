package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/3/17.
 */
@Entity
@Table(name = "user_inf")
public class UserInf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "email")
    private String email;


    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "address")
    private String address;


    @Basic
    @Column(name = "sex")
    private String sex;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "created_at")
    private Integer createdAt;

    @Basic
    @Column(name = "updated_at")
    private Integer updatedAt;

    @Basic
    @Column(name = "created_by")
    private Timestamp createdBy;

    @Basic
    @Column(name = "updated_by")
    private Timestamp updatedBy;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }


    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Timestamp createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Timestamp updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInf userInf = (UserInf) o;

        if (id != userInf.id) return false;
        if (firstName != null ? !firstName.equals(userInf.firstName) : userInf.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userInf.lastName) : userInf.lastName != null) return false;
        if (email != null ? !email.equals(userInf.email) : userInf.email != null) return false;
        if (phone != null ? !phone.equals(userInf.phone) : userInf.phone != null) return false;
        if (address != null ? !address.equals(userInf.address) : userInf.address != null) return false;
        if (sex != null ? !sex.equals(userInf.sex) : userInf.sex != null) return false;
        if (status != null ? !status.equals(userInf.status) : userInf.status != null) return false;
        if (createdAt != null ? !createdAt.equals(userInf.createdAt) : userInf.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(userInf.updatedAt) : userInf.updatedAt != null) return false;
        if (createdBy != null ? !createdBy.equals(userInf.createdBy) : userInf.createdBy != null) return false;
        if (updatedBy != null ? !updatedBy.equals(userInf.updatedBy) : userInf.updatedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (updatedBy != null ? updatedBy.hashCode() : 0);
        return result;
    }
}
