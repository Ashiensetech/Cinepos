package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

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

    @OneToOne
    @JoinColumn(name = "terminal_id",referencedColumnName = "id")
    private Terminal terminal;

    @OneToMany
    @JoinColumn(name = "sell_id",referencedColumnName = "id")
    private Set<SellsDetails> SellDetails;

    @Basic
    @Column(name = "status")
    private boolean status;



    @OneToOne
    @JoinColumn(name = "created_by",referencedColumnName = "id")
    private AuthCredential authCredential;

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

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Set<SellsDetails> getSellDetails() {
        return SellDetails;
    }

    public void setSellDetails(Set<SellsDetails> sellDetails) {
        SellDetails = sellDetails;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AuthCredential getAuthCredential() {
        return authCredential;
    }

    public void setAuthCredential(AuthCredential authCredential) {
        this.authCredential = authCredential;
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
        if (status != sells.status) return false;
        if (sellingComment != null ? !sellingComment.equals(sells.sellingComment) : sells.sellingComment != null)
            return false;
        if (terminal != null ? !terminal.equals(sells.terminal) : sells.terminal != null) return false;
        if (SellDetails != null ? !SellDetails.equals(sells.SellDetails) : sells.SellDetails != null) return false;
        if (authCredential != null ? !authCredential.equals(sells.authCredential) : sells.authCredential != null)
            return false;
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
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + (SellDetails != null ? SellDetails.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (authCredential != null ? authCredential.hashCode() : 0);
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
                ", terminal=" + terminal +
                ", SellDetails=" + SellDetails +
                ", status=" + status +
                ", authCredential=" + authCredential +
                ", createdAt=" + createdAt +
                '}';
    }
}
