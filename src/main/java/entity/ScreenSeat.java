package entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/9/17.
 */
@Entity
@Table(name = "screen_seat", schema = "")
public class ScreenSeat {
    private int id;
    private Integer screenId;
    private Integer seatTypeId;
    private String name;
    private Integer createdBy;
    private Timestamp createdAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "screen_id")
    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    @Basic
    @Column(name = "seat_type_id")
    public Integer getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "created_by")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "ScreenSeat{" +
                "id=" + id +
                ", screenId=" + screenId +
                ", seatTypeId=" + seatTypeId +
                ", name='" + name + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScreenSeat that = (ScreenSeat) o;

        if (id != that.id) return false;
        if (screenId != null ? !screenId.equals(that.screenId) : that.screenId != null) return false;
        if (seatTypeId != null ? !seatTypeId.equals(that.seatTypeId) : that.seatTypeId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (screenId != null ? screenId.hashCode() : 0);
        result = 31 * result + (seatTypeId != null ? seatTypeId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
