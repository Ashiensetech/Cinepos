package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Sarwar on 1/18/2017.
 */
@Entity
@Table(name = "combo")
public class Combo {
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "combo_name")
    private String comboName;

    @Column(name = "details")
    private String details;

    @Column(name="type")
    private String comboType;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "combo_id")
    private List<ComboProduct> comboProducts;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private int status;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getComboType() {
        return comboType;
    }

    public void setComboType(String comboType) {
        this.comboType = comboType;
    }

    public List<ComboProduct> getComboProducts() {
        return comboProducts;
    }

    public void setComboProducts(List<ComboProduct> comboProducts) {
        this.comboProducts = comboProducts;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

        Combo combo = (Combo) o;

        if (Id != combo.Id) return false;
        if (Float.compare(combo.price, price) != 0) return false;
        if (status != combo.status) return false;
        if (createdBy != combo.createdBy) return false;
        if (comboName != null ? !comboName.equals(combo.comboName) : combo.comboName != null) return false;
        if (details != null ? !details.equals(combo.details) : combo.details != null) return false;
        if (comboType != null ? !comboType.equals(combo.comboType) : combo.comboType != null) return false;
        if (comboProducts != null ? !comboProducts.equals(combo.comboProducts) : combo.comboProducts != null)
            return false;
        if (startDate != null ? !startDate.equals(combo.startDate) : combo.startDate != null) return false;
        if (endDate != null ? !endDate.equals(combo.endDate) : combo.endDate != null) return false;
        return createdAt != null ? createdAt.equals(combo.createdAt) : combo.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (comboName != null ? comboName.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (comboType != null ? comboType.hashCode() : 0);
        result = 31 * result + (comboProducts != null ? comboProducts.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + status;
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "Id=" + Id +
                ", comboName='" + comboName + '\'' +
                ", details='" + details + '\'' +
                ", comboType='" + comboType + '\'' +
                ", comboProducts=" + comboProducts +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
