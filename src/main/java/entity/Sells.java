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
    private Float sellingAmount;

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

    public Float getSellingAmount() {
        return sellingAmount;
    }

    public void setSellingAmount(Float sellingAmount) {
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

        Sells sell = (Sells) o;

        if (id != sell.id) return false;
        if (isCombo != sell.isCombo) return false;
        if (quantity != sell.quantity) return false;
        if (terminalId != sell.terminalId) return false;
        if (status != sell.status) return false;
        if (createdBy != sell.createdBy) return false;
        if (sellingAmount != null ? !sellingAmount.equals(sell.sellingAmount) : sell.sellingAmount != null)
            return false;
        if (sellingComment != null ? !sellingComment.equals(sell.sellingComment) : sell.sellingComment != null)
            return false;
        return createdAt != null ? createdAt.equals(sell.createdAt) : sell.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sellingAmount != null ? sellingAmount.hashCode() : 0);
        result = 31 * result + (sellingComment != null ? sellingComment.hashCode() : 0);
        result = 31 * result + (isCombo ? 1 : 0);
        result = 31 * result + quantity;
        result = 31 * result + terminalId;
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + createdBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sell{" +
                "id=" + id +
                ", sellingAmount=" + sellingAmount +
                ", sellingComment='" + sellingComment + '\'' +
                ", isCombo=" + isCombo +
                ", quantity=" + quantity +
                ", terminalId=" + terminalId +
                ", status=" + status +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
