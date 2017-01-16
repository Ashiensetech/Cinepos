package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/13/2017.
 */
@Entity
@Table(name = "concession_product")
public class ConcessionProduct {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "annotation")
    private String annotation;

    @Basic
    @Column(name = "category_id")
    private int category_id;

    @Basic
    @Column(name = "unit")
    private int unit;

    @Basic
    @Column(name = "remote_print")
    private int remotePrint;

    @Basic
    @Column(name = "is_combo")
    private int isCombo;

    @Basic
    @Column(name = "status")
    private int status;

    @Basic
    @Column(name = "selling_price")
    private float selling_price;

    @Basic
    @Column(name = "buying_price")
    private float buying_price;

    @Basic
    @Column(name = "is_price_shift")
    private int isPriceShift;

    @Basic
    @Column(name = "created_by")
    private int createdBy;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getRemotePrint() {
        return remotePrint;
    }

    public void setRemotePrint(int remotePrint) {
        this.remotePrint = remotePrint;
    }

    public int getIsCombo() {
        return isCombo;
    }

    public void setIsCombo(int isCombo) {
        this.isCombo = isCombo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(float selling_price) {
        this.selling_price = selling_price;
    }

    public float getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(float buying_price) {
        this.buying_price = buying_price;
    }

    public int getIsPriceShift() {
        return isPriceShift;
    }

    public void setIsPriceShift(int isPriceShift) {
        this.isPriceShift = isPriceShift;
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

        ConcessionProduct that = (ConcessionProduct) o;

        if (id != that.id) return false;
        if (category_id != that.category_id) return false;
        if (unit != that.unit) return false;
        if (remotePrint != that.remotePrint) return false;
        if (isCombo != that.isCombo) return false;
        if (status != that.status) return false;
        if (Float.compare(that.selling_price, selling_price) != 0) return false;
        if (Float.compare(that.buying_price, buying_price) != 0) return false;
        if (isPriceShift != that.isPriceShift) return false;
        if (createdBy != that.createdBy) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (annotation != null ? !annotation.equals(that.annotation) : that.annotation != null) return false;
        return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (annotation != null ? annotation.hashCode() : 0);
        result = 31 * result + category_id;
        result = 31 * result + unit;
        result = 31 * result + remotePrint;
        result = 31 * result + isCombo;
        result = 31 * result + status;
        result = 31 * result + (selling_price != +0.0f ? Float.floatToIntBits(selling_price) : 0);
        result = 31 * result + (buying_price != +0.0f ? Float.floatToIntBits(buying_price) : 0);
        result = 31 * result + isPriceShift;
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConcessionProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", annotation='" + annotation + '\'' +
                ", category_id=" + category_id +
                ", unit=" + unit +
                ", remotePrint=" + remotePrint +
                ", isCombo=" + isCombo +
                ", status=" + status +
                ", selling_price=" + selling_price +
                ", buying_price=" + buying_price +
                ", isPriceShift=" + isPriceShift +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
