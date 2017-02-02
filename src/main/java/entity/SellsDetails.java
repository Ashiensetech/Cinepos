package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Sarwar on 2/2/2017.
 */

@Entity
@Table(name = "sells_details")
public class SellsDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "sell_id")
    private Integer sellId;

    @Basic
    @Column(name = "concession_product_id")
    private Integer concessionProductId;

    @Basic
    @Column(name = "combo_id")
    private Integer comboId;

    @Basic
    @Column(name = "seat_type_id")
    private Integer seatTypeId;

    @Basic
    @Column(name = "user_id")
    private Integer userId;

    @Basic
    @Column(name = "unit_selling_amount")
    private int unitSellingAmount;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @Basic
    @Column(name = "created_by")
    private int createdBy;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne
    @JoinColumn(name = "concession_product_id",referencedColumnName = "id",insertable = false, updatable = false)
    private ConcessionProduct concessionProduct;

    @OneToOne
    @JoinColumn(name = "combo_id",referencedColumnName = "id",insertable = false, updatable = false)
    private Combo combo;

    @OneToOne
    @JoinColumn(name = "seat_type_id",referencedColumnName = "id",insertable = false, updatable = false)
    private SeatType seatType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public Integer getConcessionProductId() {
        return concessionProductId;
    }

    public void setConcessionProductId(Integer concessionProductId) {
        this.concessionProductId = concessionProductId;
    }

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getUnitSellingAmount() {
        return unitSellingAmount;
    }

    public void setUnitSellingAmount(int unitSellingAmount) {
        this.unitSellingAmount = unitSellingAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Combo getCombo() {
        return combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
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

        SellsDetails that = (SellsDetails) o;

        if (id != that.id) return false;
        if (unitSellingAmount != that.unitSellingAmount) return false;
        if (quantity != that.quantity) return false;
        if (createdBy != that.createdBy) return false;
        if (sellId != null ? !sellId.equals(that.sellId) : that.sellId != null) return false;
        if (concessionProductId != null ? !concessionProductId.equals(that.concessionProductId) : that.concessionProductId != null)
            return false;
        if (comboId != null ? !comboId.equals(that.comboId) : that.comboId != null) return false;
        if (seatTypeId != null ? !seatTypeId.equals(that.seatTypeId) : that.seatTypeId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (concessionProduct != null ? !concessionProduct.equals(that.concessionProduct) : that.concessionProduct != null)
            return false;
        if (combo != null ? !combo.equals(that.combo) : that.combo != null) return false;
        return seatType != null ? seatType.equals(that.seatType) : that.seatType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sellId != null ? sellId.hashCode() : 0);
        result = 31 * result + (concessionProductId != null ? concessionProductId.hashCode() : 0);
        result = 31 * result + (comboId != null ? comboId.hashCode() : 0);
        result = 31 * result + (seatTypeId != null ? seatTypeId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + unitSellingAmount;
        result = 31 * result + quantity;
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (concessionProduct != null ? concessionProduct.hashCode() : 0);
        result = 31 * result + (combo != null ? combo.hashCode() : 0);
        result = 31 * result + (seatType != null ? seatType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SellsDetails{" +
                "id=" + id +
                ", sellId=" + sellId +
                ", concessionProductId=" + concessionProductId +
                ", comboId=" + comboId +
                ", seatTypeId=" + seatTypeId +
                ", userId=" + userId +
                ", unitSellingAmount=" + unitSellingAmount +
                ", quantity=" + quantity +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", concessionProduct=" + concessionProduct +
                ", combo=" + combo +
                ", seatType=" + seatType +
                '}';
    }
}
