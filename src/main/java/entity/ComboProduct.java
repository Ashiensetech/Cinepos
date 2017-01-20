package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/18/2017.
 */
@Entity
@Table(name = "combo_product")
public class ComboProduct {
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name = "combo_id")
    private Integer comboId;

    @Column(name="type")
    private String comboProductType;


    @Column(name = "concession_product_id")
    private Integer concessionProductId;


    @Column(name = "ticket_id")
    private Integer ticketId;

    @Column(name = "created_by")
    private int createdBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne
    @JoinColumn(name = "concession_product_id",referencedColumnName = "id",insertable = false, updatable = false)
    private ConcessionProduct concessionProduct;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public String getComboProductType() {
        return comboProductType;
    }

    public void setComboProductType(String comboProductType) {
        this.comboProductType = comboProductType;
    }

    public Integer getConcessionProductId() {
        return concessionProductId;
    }

    public void setConcessionProductId(Integer concessionProductId) {
        this.concessionProductId = concessionProductId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
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

    public ConcessionProduct getConcessionProduct() {
        return concessionProduct;
    }

    public void setConcessionProduct(ConcessionProduct concessionProduct) {
        this.concessionProduct = concessionProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComboProduct that = (ComboProduct) o;

        if (Id != that.Id) return false;
        if (createdBy != that.createdBy) return false;
        if (comboId != null ? !comboId.equals(that.comboId) : that.comboId != null) return false;
        if (comboProductType != null ? !comboProductType.equals(that.comboProductType) : that.comboProductType != null)
            return false;
        if (concessionProductId != null ? !concessionProductId.equals(that.concessionProductId) : that.concessionProductId != null)
            return false;
        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return concessionProduct != null ? concessionProduct.equals(that.concessionProduct) : that.concessionProduct == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (comboId != null ? comboId.hashCode() : 0);
        result = 31 * result + (comboProductType != null ? comboProductType.hashCode() : 0);
        result = 31 * result + (concessionProductId != null ? concessionProductId.hashCode() : 0);
        result = 31 * result + (ticketId != null ? ticketId.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (concessionProduct != null ? concessionProduct.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComboProduct{" +
                "Id=" + Id +
                ", comboId=" + comboId +
                ", comboProductType='" + comboProductType + '\'' +
                ", concessionProductId=" + concessionProductId +
                ", ticketId=" + ticketId +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", concessionProduct=" + concessionProduct +
                '}';
    }

    public enum ComboType {
        product,
        ticket;
    }

}
