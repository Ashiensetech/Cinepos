package entity.entityView.report;

import javax.persistence.*;


@Entity
@Table(name = "product_summary_view")
public class ProductSummaryReportView {

    @javax.persistence.Id
    @Column(name = "ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "Product")
    private String Product;

    @Column(name = "Price")
    private float price;

    @Column(name = "StockUnit")
    private int StockUnit;

    @Column(name = "StockValue")
    private String StockValue;

    @Column(name = "CreatedBy")
    private Integer CreateBy;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStockUnit() {
        return StockUnit;
    }

    public void setStockUnit(int stockUnit) {
        StockUnit = stockUnit;
    }

    public String getStockValue() {
        return StockValue;
    }

    public void setStockValue(String stockValue) {
        StockValue = stockValue;
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
        if (Float.compare(that.price, price) != 0) return false;
        if (StockUnit != that.StockUnit) return false;
        if (Product != null ? !Product.equals(that.Product) : that.Product != null) return false;
        if (StockValue != null ? !StockValue.equals(that.StockValue) : that.StockValue != null) return false;
        return CreateDate != null ? CreateDate.equals(that.CreateDate) : that.CreateDate == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (Product != null ? Product.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + StockUnit;
        result = 31 * result + (StockValue != null ? StockValue.hashCode() : 0);
        result = 31 * result + (CreateDate != null ? CreateDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductReportView{" +
                "Id=" + Id +
                ", Product='" + Product + '\'' +
                ", price=" + price +
                ", StockUnit=" + StockUnit +
                ", StockValue='" + StockValue + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
                '}';
    }
}
