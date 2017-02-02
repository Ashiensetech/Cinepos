package entity.reportEntity;

import javax.persistence.*;

/**
 * Created by Sarwar on 2/1/2017.
 */
@Entity
@Table(name = "viewproductsales")
public class ProductSaleReportView{

    @javax.persistence.Id
    @Column(name = "id")
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

    @Override
    public String toString() {
        return "ProductSaleReportView{" +
                "Id=" + Id +
                ", Product='" + Product + '\'' +
                ", price=" + price +
                ", StockUnit=" + StockUnit +
                ", StockValue='" + StockValue + '\'' +
                '}';
    }
}
