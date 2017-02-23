package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.sells.SellsJsonView;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Sarwar on 2/2/2017.
 */
@Entity
@Table(name = "sells")
public class Sells {

    @JsonView(SellsJsonView.Basic.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonView(SellsJsonView.Basic.class)
    @Basic
    @Column(name = "selling_amount")
    private double sellingAmount;

    @JsonView(SellsJsonView.Basic.class)
    @Basic
    @Column(name = "selling_comment")
    private String sellingComment;

    @JsonView(SellsJsonView.Basic.class)
    @Basic
    @Column(name = "is_combo")
    private boolean isCombo;

    @JsonView(SellsJsonView.Basic.class)
    @Basic
    @Column(name = "product_quantity")
    private int productQuantity;

    @JsonView(SellsJsonView.Basic.class)
    @Basic
    @Column(name = "ticket_quantity")
    private int ticketQuantity;

    @JsonView(SellsJsonView.Summary.class)
    @OneToOne
    @JoinColumn(name = "screen_id",referencedColumnName = "id")
    private Screen screen;

    @JsonView(SellsJsonView.Summary.class)
    @OneToOne
    @JoinColumn(name = "terminal_id",referencedColumnName = "id")
    private Terminal terminal;

    @JsonView(SellsJsonView.Summary.class)
    @OneToMany
    @JoinColumn(name = "sell_id",referencedColumnName = "id",updatable = false)
    private Set<SellsDetails> sellDetails;

    @JsonView(SellsJsonView.Summary.class)
    @Basic
    @Column(name = "status")
    private boolean status;


    @JsonIgnore
//    @JsonView(SellsJsonView.Summary.class)
    @OneToOne
    @JoinColumn(name = "created_by",referencedColumnName = "id")
    private AuthCredential saleBy;

    @JsonView(SellsJsonView.Summary.class)
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Set<SellsDetails> getSellDetails() {
        return sellDetails;
    }

    public void setSellDetails(Set<SellsDetails> sellDetails) {
        this.sellDetails = sellDetails;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AuthCredential getSaleBy() {
        return saleBy;
    }

    public void setSaleBy(AuthCredential saleBy) {
        this.saleBy = saleBy;
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
        if (productQuantity != sells.productQuantity) return false;
        if (ticketQuantity != sells.ticketQuantity) return false;
        if (status != sells.status) return false;
        if (sellingComment != null ? !sellingComment.equals(sells.sellingComment) : sells.sellingComment != null)
            return false;
        if (screen != null ? !screen.equals(sells.screen) : sells.screen != null) return false;
        if (terminal != null ? !terminal.equals(sells.terminal) : sells.terminal != null) return false;
        if (sellDetails != null ? !sellDetails.equals(sells.sellDetails) : sells.sellDetails != null) return false;
        if (saleBy != null ? !saleBy.equals(sells.saleBy) : sells.saleBy != null)
            return false;
        return !(createdAt != null ? !createdAt.equals(sells.createdAt) : sells.createdAt != null);

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
        result = 31 * result + productQuantity;
        result = 31 * result + ticketQuantity;
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + (sellDetails != null ? sellDetails.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (saleBy != null ? saleBy.hashCode() : 0);
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
                ", productQuantity=" + productQuantity +
                ", ticketQuantity=" + ticketQuantity +
                ", screen=" + screen +
                ", terminal=" + terminal +
                ", sellDetails=" + sellDetails +
                ", status=" + status +
                ", saleBy=" + saleBy +
                ", createdAt=" + createdAt +
                '}';
    }
}
