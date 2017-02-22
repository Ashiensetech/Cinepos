package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.combo.ComboDetailsJsonView;
import entity.app.jsonview.combo.ComboJsonView;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 1/18/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "combo_details")
public class ComboDetails {

    @JsonView(ComboDetailsJsonView.Basic.class)
    @javax.persistence.Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @JsonView(ComboDetailsJsonView.Basic.class)
    @Column(name = "combo_id")
    private Integer comboId;

    @JsonView(ComboDetailsJsonView.Basic.class)
    @Column(name="type")
    private String comboProductType;

    @JsonView(ComboDetailsJsonView.Basic.class)
    @Column(name = "concession_product_id")
    private Integer concessionProductId;

    @JsonView(ComboDetailsJsonView.Basic.class)
    @Column(name = "product_quantity")
    private Integer productQuantity;

    @JsonView(ComboDetailsJsonView.Basic.class)
    @Column(name = "ticket_quantity")
    private Integer ticketQuantity;

    @JsonView(ComboDetailsJsonView.Basic.class)
    @Column(name = "seat_type_id")
    private Integer seatTypeId;

    @JsonView(ComboDetailsJsonView.Basic.class)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @JsonIgnore
    @Column(name = "created_by")
    private int createdBy;

    @JsonIgnore
    @Column(name = "created_at")
    private Timestamp createdAt;

    @JsonView(ComboDetailsJsonView.Summary.class)
    @OneToOne
    @JoinColumn(name = "concession_product_id",referencedColumnName = "id",insertable = false, updatable = false)
    private ConcessionProduct concessionProduct;

    @JsonView(ComboDetailsJsonView.Summary.class)
    @OneToOne
    @JoinColumn(name = "seat_type_id",referencedColumnName = "id",insertable = false, updatable = false,nullable = true)
    private SeatType seatType;

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

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Integer getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(Integer ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
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

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComboDetails that = (ComboDetails) o;

        if (Id != that.Id) return false;
        if (createdBy != that.createdBy) return false;
        if (comboId != null ? !comboId.equals(that.comboId) : that.comboId != null) return false;
        if (comboProductType != null ? !comboProductType.equals(that.comboProductType) : that.comboProductType != null)
            return false;
        if (concessionProductId != null ? !concessionProductId.equals(that.concessionProductId) : that.concessionProductId != null)
            return false;
        if (productQuantity != null ? !productQuantity.equals(that.productQuantity) : that.productQuantity != null)
            return false;
        if (ticketQuantity != null ? !ticketQuantity.equals(that.ticketQuantity) : that.ticketQuantity != null)
            return false;
        if (seatTypeId != null ? !seatTypeId.equals(that.seatTypeId) : that.seatTypeId != null) return false;
        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (concessionProduct != null ? !concessionProduct.equals(that.concessionProduct) : that.concessionProduct != null)
            return false;
        return seatType != null ? seatType.equals(that.seatType) : that.seatType == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (comboId != null ? comboId.hashCode() : 0);
        result = 31 * result + (comboProductType != null ? comboProductType.hashCode() : 0);
        result = 31 * result + (concessionProductId != null ? concessionProductId.hashCode() : 0);
        result = 31 * result + (productQuantity != null ? productQuantity.hashCode() : 0);
        result = 31 * result + (ticketQuantity != null ? ticketQuantity.hashCode() : 0);
        result = 31 * result + (seatTypeId != null ? seatTypeId.hashCode() : 0);
        result = 31 * result + (ticketId != null ? ticketId.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (concessionProduct != null ? concessionProduct.hashCode() : 0);
        result = 31 * result + (seatType != null ? seatType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComboDetails{" +
                "Id=" + Id +
                ", comboId=" + comboId +
                ", comboProductType='" + comboProductType + '\'' +
                ", concessionProductId=" + concessionProductId +
                ", productQuantity=" + productQuantity +
                ", ticketQuantity=" + ticketQuantity +
                ", seatTypeId=" + seatTypeId +
                ", ticketId=" + ticketId +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", concessionProduct=" + concessionProduct +
                ", seatType=" + seatType +
                '}';
    }

    public enum ComboType {
        product,
        ticket;
    }

}
