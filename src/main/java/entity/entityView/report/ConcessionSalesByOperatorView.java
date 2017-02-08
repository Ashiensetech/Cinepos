package entity.entityView.report;
import entity.AuthCredential;

import javax.persistence.*;


@Entity
@Table(name = "conc_sales_by_operator_view")
public class ConcessionSalesByOperatorView {

    @javax.persistence.Id
    @Column(name = "SD_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SD_Id;

    @Column(name = "Product")
    private String Product;

    @Column(name = "QTY")
    private int QTY;

    @Column(name = "UnitPrice")
    private float UnitPrice;

    @Column(name = "Gross")
    private float Gross;

    @Column(name = "Oparetor")
    private int Oparetor;

    @Column(name = "CreateDate")
    private String 	CreateDate;

    @OneToOne
    @JoinColumn(name = "Oparetor",referencedColumnName = "id")
    private AuthCredential authCredential;

    public int getSD_Id() {
        return SD_Id;
    }

    public void setSD_Id(int SD_Id) {
        this.SD_Id = SD_Id;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
    }

    public float getGross() {
        return Gross;
    }

    public void setGross(float gross) {
        Gross = gross;
    }

    public int getOparetor() {
        return Oparetor;
    }

    public void setOparetor(int oparetor) {
        Oparetor = oparetor;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public AuthCredential getAuthCredential() {
        return authCredential;
    }

    public void setAuthCredential(AuthCredential authCredential) {
        this.authCredential = authCredential;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcessionSalesByOperatorView that = (ConcessionSalesByOperatorView) o;

        if (SD_Id != that.SD_Id) return false;
        if (QTY != that.QTY) return false;
        if (Float.compare(that.UnitPrice, UnitPrice) != 0) return false;
        if (Float.compare(that.Gross, Gross) != 0) return false;
        if (Oparetor != that.Oparetor) return false;
        if (Product != null ? !Product.equals(that.Product) : that.Product != null) return false;
        if (CreateDate != null ? !CreateDate.equals(that.CreateDate) : that.CreateDate != null) return false;
        return authCredential != null ? authCredential.equals(that.authCredential) : that.authCredential == null;
    }

    @Override
    public int hashCode() {
        int result = SD_Id;
        result = 31 * result + (Product != null ? Product.hashCode() : 0);
        result = 31 * result + QTY;
        result = 31 * result + (UnitPrice != +0.0f ? Float.floatToIntBits(UnitPrice) : 0);
        result = 31 * result + (Gross != +0.0f ? Float.floatToIntBits(Gross) : 0);
        result = 31 * result + Oparetor;
        result = 31 * result + (CreateDate != null ? CreateDate.hashCode() : 0);
        result = 31 * result + (authCredential != null ? authCredential.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ConcessionSalesByOperatorView{" +
                "SD_Id=" + SD_Id +
                ", Product='" + Product + '\'' +
                ", QTY=" + QTY +
                ", UnitPrice=" + UnitPrice +
                ", Gross=" + Gross +
                ", Oparetor=" + Oparetor +
                ", CreateDate='" + CreateDate + '\'' +
                ", authCredential=" + authCredential +
                '}';
    }
}
