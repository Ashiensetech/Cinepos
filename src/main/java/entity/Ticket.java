package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by mi on 1/24/17.
 */
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "seat_type_id")
    private int seatTypeId;


    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "annotation")
    private String annotation;

    @Basic
    @Column(name = "printed_price")
    private BigDecimal printedPrice;

    @Basic
    @Column(name = "sale_channels")
    private String saleChannels;

    @Basic
    @Column(name = "vat_id")
    private Integer vatId;

    @Basic
    @Column(name = "is_child")
    private boolean isChild;

    @Basic
    @Column(name = "is_adult")
    private boolean isAdult;

    @Basic
    @Column(name = "status")
    private boolean status;

    @Basic
    @Column(name = "start_date")
    private Date startDate;

    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(int seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public BigDecimal getPrintedPrice() {
        return printedPrice;
    }

    public void setPrintedPrice(BigDecimal printedPrice) {
        this.printedPrice = printedPrice;
    }

    public String getSaleChannels() {
        return saleChannels;
    }

    public void setSaleChannels(String saleChannels) {
        this.saleChannels = saleChannels;
    }

    public Integer getVatId() {
        return vatId;
    }

    public void setVatId(Integer vatId) {
        this.vatId = vatId;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setIsChild(boolean isChild) {
        this.isChild = isChild;
    }

    public boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
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

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) return false;
        if (seatTypeId != ticket.seatTypeId) return false;
        if (isChild != ticket.isChild) return false;
        if (isAdult != ticket.isAdult) return false;
        if (status != ticket.status) return false;
        if (name != null ? !name.equals(ticket.name) : ticket.name != null) return false;
        if (description != null ? !description.equals(ticket.description) : ticket.description != null) return false;
        if (annotation != null ? !annotation.equals(ticket.annotation) : ticket.annotation != null) return false;
        if (printedPrice != null ? !printedPrice.equals(ticket.printedPrice) : ticket.printedPrice != null)
            return false;
        if (saleChannels != null ? !saleChannels.equals(ticket.saleChannels) : ticket.saleChannels != null)
            return false;
        if (vatId != null ? !vatId.equals(ticket.vatId) : ticket.vatId != null) return false;
        if (startDate != null ? !startDate.equals(ticket.startDate) : ticket.startDate != null) return false;
        if (endDate != null ? !endDate.equals(ticket.endDate) : ticket.endDate != null) return false;
        if (createdBy != null ? !createdBy.equals(ticket.createdBy) : ticket.createdBy != null) return false;
        if (createdAt != null ? !createdAt.equals(ticket.createdAt) : ticket.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + seatTypeId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (annotation != null ? annotation.hashCode() : 0);
        result = 31 * result + (printedPrice != null ? printedPrice.hashCode() : 0);
        result = 31 * result + (saleChannels != null ? saleChannels.hashCode() : 0);
        result = 31 * result + (vatId != null ? vatId.hashCode() : 0);
        result = 31 * result + (isChild ? 1 : 0);
        result = 31 * result + (isAdult ? 1 : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
