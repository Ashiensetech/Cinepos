package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.concession.product.ConcessionProductCategoryJsonView;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/9/2017.
 */
@Entity
@Table(name = "concession_product_category")
public class ConcessionProductCategory {

    @JsonView(ConcessionProductCategoryJsonView.Basic.class)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(ConcessionProductCategoryJsonView.Basic.class)
    @Basic
    @Column(name = "name")
    private String name;

    @JsonView(ConcessionProductCategoryJsonView.Basic.class)
    @Basic
    @Column(name = "status")
    private int status;

    @JsonIgnore
    @Basic
    @Column(name = "created_by",nullable = true)
    private int createdBy;

    @JsonIgnore
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        ConcessionProductCategory that = (ConcessionProductCategory) o;

        if (id != that.id) return false;
        if (status != that.status) return false;
        if (createdBy != that.createdBy) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConcessionProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
