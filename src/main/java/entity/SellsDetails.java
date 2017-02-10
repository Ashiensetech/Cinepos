package entity;

import javax.persistence.*;
import java.sql.Timestamp;


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

//    @Basic
//    @Column(name = "concession_product_id")
//    private Integer concessionProductId;
//
//    @Basic
//    @Column(name = "combo_id")
//    private Integer comboId;
//
//    @Basic
//    @Column(name = "seat_type_id")
//    private Integer seatTypeId;

    @Basic
    @Column(name = "user_id")
    private Integer userId;

    @Basic
    @Column(name = "unit_selling_amount")
    private float unitSellingAmount;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @Basic
    @Column(name = "selling_type")
    private String sellingType;

    @Basic
    @Column(name = "created_by")
    private int createdBy;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne
    @JoinColumn(name = "concession_product_id",referencedColumnName = "id")
    private ConcessionProduct concessionProduct;

    @OneToOne
    @JoinColumn(name = "combo_id",referencedColumnName = "id")
    private Combo combo;

    @OneToOne
    @JoinColumn(name = "ticket_id",referencedColumnName = "id")
    private Ticket ticket;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public float getUnitSellingAmount() {
        return unitSellingAmount;
    }

    public void setUnitSellingAmount(float unitSellingAmount) {
        this.unitSellingAmount = unitSellingAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSellingType() {
        return sellingType;
    }

    public void setSellingType(String sellingType) {
        this.sellingType = sellingType;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SellsDetails that = (SellsDetails) o;

        if (id != that.id) return false;
        if (Float.compare(that.unitSellingAmount, unitSellingAmount) != 0) return false;
        if (quantity != that.quantity) return false;
        if (createdBy != that.createdBy) return false;
        if (sellId != null ? !sellId.equals(that.sellId) : that.sellId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (sellingType != null ? !sellingType.equals(that.sellingType) : that.sellingType != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (concessionProduct != null ? !concessionProduct.equals(that.concessionProduct) : that.concessionProduct != null)
            return false;
        if (combo != null ? !combo.equals(that.combo) : that.combo != null) return false;
        return ticket != null ? ticket.equals(that.ticket) : that.ticket == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sellId != null ? sellId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (unitSellingAmount != +0.0f ? Float.floatToIntBits(unitSellingAmount) : 0);
        result = 31 * result + quantity;
        result = 31 * result + (sellingType != null ? sellingType.hashCode() : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (concessionProduct != null ? concessionProduct.hashCode() : 0);
        result = 31 * result + (combo != null ? combo.hashCode() : 0);
        result = 31 * result + (ticket != null ? ticket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SellsDetails{" +
                "id=" + id +
                ", sellId=" + sellId +
                ", userId=" + userId +
                ", unitSellingAmount=" + unitSellingAmount +
                ", quantity=" + quantity +
                ", sellingType='" + sellingType + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", concessionProduct=" + concessionProduct +
                ", combo=" + combo +
                ", ticket=" + ticket +
                '}';
    }
}
