package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import entity.iface.AdminCategory;
import entity.iface.AppCategory;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by mi on 8/1/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "category" ) //catalog = "rentguru24"
public class Category implements AdminCategory,AppCategory {
    private int id;
    private String name;
    private Integer parentId;
    private int sortedOrder;
    private int productCount;
    private int createdBy;
    private boolean isSubcategory;
    private Timestamp createdDate;
    private List<Category> subcategory;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "is_subcategory")
    public boolean getIsSubcategory() {
        return isSubcategory;
    }

    public void setIsSubcategory(boolean isSubcategory) {
        this.isSubcategory = isSubcategory;
    }

    @Basic
    @Column(name = "sorted_order")
    public int getSortedOrder() {
        return sortedOrder;
    }

    public void setSortedOrder(int sortedOrder) {
        this.sortedOrder = sortedOrder;
    }



    @Basic
    @Column(name = "product_count")
    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Basic
    @Column(name = "created_by")
    @JsonIgnore
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_date")
    @JsonIgnore
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
    public List<Category> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<Category> subcategory) {
        this.subcategory = subcategory;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", sortedOrder=" + sortedOrder +
                ", createdBy=" + createdBy +
                ", isSubcategory=" + isSubcategory +
                ", createdDate=" + createdDate +
                ", subcategory=" + subcategory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != category.id) return false;
        if (sortedOrder != category.sortedOrder) return false;
        if (createdBy != category.createdBy) return false;
        if (isSubcategory != category.isSubcategory) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (parentId != null ? !parentId.equals(category.parentId) : category.parentId != null) return false;
        if (createdDate != null ? !createdDate.equals(category.createdDate) : category.createdDate != null)
            return false;
        return !(subcategory != null ? !subcategory.equals(category.subcategory) : category.subcategory != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + sortedOrder;
        result = 31 * result + createdBy;
        result = 31 * result + (isSubcategory ? 1 : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (subcategory != null ? subcategory.hashCode() : 0);
        return result;
    }
}
