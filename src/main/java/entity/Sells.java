package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Sarwar on 2/2/2017.
 */
@Entity
@Table(name = "sells")
public class Sells {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "selling_amount")
    private double sellingAmount;

    @Basic
    @Column(name = "selling_comment")
    private String sellingComment;

    @Basic
    @Column(name = "is_combo")
    private boolean isCombo;

    @Basic
    @Column(name = "quantity")
    private int quantity;

    @Basic
    @Column(name = "terminal_id")
    private int terminalId;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "sell_id")
    private List<SellsDetails> SellDetails;

    @Basic
    @Column(name = "status")
    private boolean status;

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

    public double getSellingAmount() {
        return sellingAmount;
    }

    public void setSellingAmount(double sellingAmount) {
        this.sellingAmount = sellingAmount;
    }

    public String getSellingComment() {
        return sellingComment;
    }

    public void setSellingComment(String sellingComment) {
        this.sellingComment = sellingComment;
    }

    public boolean isCombo() {
        return isCombo;
    }

    public void setCombo(boolean combo) {
        isCombo = combo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public List<SellsDetails> getSellDetails() {
        return SellDetails;
    }

    public void setSellDetails(List<SellsDetails> sellDetails) {
        SellDetails = sellDetails;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

        Sells sells = (Sells) o;

        if (id != sells.id) return false;
        if (Double.compare(sells.sellingAmount, sellingAmount) != 0) return false;
        if (isCombo != sells.isCombo) return false;
        if (quantity != sells.quantity) return false;
        if (terminalId != sells.terminalId) return false;
        if (status != sells.status) return false;
        if (createdBy != sells.createdBy) return false;
        if (sellingComment != null ? !sellingComment.equals(sells.sellingComment) : sells.sellingComment != null)
            return false;
        if (SellDetails != null ? !SellDetails.equals(sells.SellDetails) : sells.SellDetails != null) return false;
        return createdAt != null ? createdAt.equals(sells.createdAt) : sells.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(sellingAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sellingComment != null ? sellingComment.hashCode() : 0);
        result = 31 * result + (isCombo ? 1 : 0);
        result = 31 * result + quantity;
        result = 31 * result + terminalId;
        result = 31 * result + (SellDetails != null ? SellDetails.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sells{" +
                "id=" + id +
                ", sellingAmount=" + sellingAmount +
                ", sellingComment='" + sellingComment + '\'' +
                ", isCombo=" + isCombo +
                ", quantity=" + quantity +
                ", terminalId=" + terminalId +
                ", SellDetails=" + SellDetails +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
