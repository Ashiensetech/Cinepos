package entity.entityView.report;

import javax.persistence.*;


@Entity
@Table(name = "product_summary_view")
public class ProductSummaryReportView {

    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "Product")
    private String Product;

    @Column(name = "SalePrice")
    private float SalePrice;

    @Column(name = "QTY")
    private int SaleUnit;

    @Column(name = "SaleValue")
    private String SaleValue;



    @Column(name = "CreateDate")
    private String 	CreateDate;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public float getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(float salePrice) {
        SalePrice = salePrice;
    }

    public int getSaleUnit() {
        return SaleUnit;
    }

    public void setSaleUnit(int saleUnit) {
        SaleUnit = saleUnit;
    }

    public String getSaleValue() {
        return SaleValue;
    }

    public void setSaleValue(String saleValue) {
        SaleValue = saleValue;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSummaryReportView that = (ProductSummaryReportView) o;

        if (Id != that.Id) return false;
        if (Float.compare(that.SalePrice, SalePrice) != 0) return false;
        if (SaleUnit != that.SaleUnit) return false;
        if (Product != null ? !Product.equals(that.Product) : that.Product != null) return false;
        if (SaleValue != null ? !SaleValue.equals(that.SaleValue) : that.SaleValue != null) return false;
        return CreateDate != null ? CreateDate.equals(that.CreateDate) : that.CreateDate == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (Product != null ? Product.hashCode() : 0);
        result = 31 * result + (SalePrice != +0.0f ? Float.floatToIntBits(SalePrice) : 0);
        result = 31 * result + SaleUnit;
        result = 31 * result + (SaleValue != null ? SaleValue.hashCode() : 0);
        result = 31 * result + (CreateDate != null ? CreateDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductSummaryReportView{" +
                "Id=" + Id +
                ", Product='" + Product + '\'' +
                ", SalePrice=" + SalePrice +
                ", SaleUnit=" + SaleUnit +
                ", SaleValue='" + SaleValue + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
                '}';
    }
}
