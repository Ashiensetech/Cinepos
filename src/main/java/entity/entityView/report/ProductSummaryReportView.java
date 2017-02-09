package entity.entityView.report;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "product_summary_view")
public class ProductSummaryReportView {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "selling_price")
    private float salePrice;

    @Column(name = "qty")
    private int saleUnit;

    @Column(name = "total")
    private String saleValue;


    @Column(name = "created_at")
    private Date createAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public int getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(int saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(String saleValue) {
        this.saleValue = saleValue;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSummaryReportView that = (ProductSummaryReportView) o;

        if (id != that.id) return false;
        if (Float.compare(that.salePrice, salePrice) != 0) return false;
        if (saleUnit != that.saleUnit) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (saleValue != null ? !saleValue.equals(that.saleValue) : that.saleValue != null) return false;
        return !(createAt != null ? !createAt.equals(that.createAt) : that.createAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (salePrice != +0.0f ? Float.floatToIntBits(salePrice) : 0);
        result = 31 * result + saleUnit;
        result = 31 * result + (saleValue != null ? saleValue.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductSummaryReportView{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", salePrice=" + salePrice +
                ", saleUnit=" + saleUnit +
                ", saleValue='" + saleValue + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
