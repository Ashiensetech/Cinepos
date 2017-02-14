package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


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

    @OneToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private ConcessionProductCategory concessionProductCategory;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "concession_product_id",referencedColumnName = "id")
    private List<ConcessionProductImage> concessionProductImages;

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
    private Float sellingPrice;

    @Basic
    @Column(name = "buying_price")
    private Float buyingPrice;

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

    public ConcessionProductCategory getConcessionProductCategory() {
        return concessionProductCategory;
    }

    public void setConcessionProductCategory(ConcessionProductCategory concessionProductCategory) {
        this.concessionProductCategory = concessionProductCategory;
    }

    public List<ConcessionProductImage> getConcessionProductImages() {
        return concessionProductImages;
    }

    public void setConcessionProductImages(List<ConcessionProductImage> concessionProductImages) {
        this.concessionProductImages = concessionProductImages;
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

    public Float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Float buyingPrice) {
        this.buyingPrice = buyingPrice;
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
        if (unit != that.unit) return false;
        if (remotePrint != that.remotePrint) return false;
        if (isCombo != that.isCombo) return false;
        if (status != that.status) return false;
        if (isPriceShift != that.isPriceShift) return false;
        if (createdBy != that.createdBy) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (annotation != null ? !annotation.equals(that.annotation) : that.annotation != null) return false;
        if (concessionProductCategory != null ? !concessionProductCategory.equals(that.concessionProductCategory) : that.concessionProductCategory != null)
            return false;
        if (concessionProductImages != null ? !concessionProductImages.equals(that.concessionProductImages) : that.concessionProductImages != null)
            return false;
        if (sellingPrice != null ? !sellingPrice.equals(that.sellingPrice) : that.sellingPrice != null) return false;
        if (buyingPrice != null ? !buyingPrice.equals(that.buyingPrice) : that.buyingPrice != null) return false;
        return createdAt != null ? createdAt.equals(that.createdAt) : that.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (annotation != null ? annotation.hashCode() : 0);
        result = 31 * result + (concessionProductCategory != null ? concessionProductCategory.hashCode() : 0);
        result = 31 * result + (concessionProductImages != null ? concessionProductImages.hashCode() : 0);
        result = 31 * result + unit;
        result = 31 * result + remotePrint;
        result = 31 * result + isCombo;
        result = 31 * result + status;
        result = 31 * result + (sellingPrice != null ? sellingPrice.hashCode() : 0);
        result = 31 * result + (buyingPrice != null ? buyingPrice.hashCode() : 0);
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
                ", concessionProductCategory=" + concessionProductCategory +
                ", concessionProductImages=" + concessionProductImages +
                ", unit=" + unit +
                ", remotePrint=" + remotePrint +
                ", isCombo=" + isCombo +
                ", status=" + status +
                ", sellingPrice=" + sellingPrice +
                ", buyingPrice=" + buyingPrice +
                ", isPriceShift=" + isPriceShift +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
