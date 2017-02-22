package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import entity.app.jsonview.screen.ScreenSeatJsonView;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mi on 1/9/17.
 */

@Entity
@Table(name = "screen_seat")
public class ScreenSeat {
    @JsonView(ScreenSeatJsonView.Basic.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @JsonView(ScreenSeatJsonView.Basic.class)
    @Basic
    @Column(name = "screen_id")
    private Integer screenId;

    @JsonView(ScreenSeatJsonView.Summary.class)
    @OneToOne
    @JoinColumn(name = "seat_type_id",referencedColumnName = "id")
    private SeatType seatType;

    @JsonView(ScreenSeatJsonView.Summary.class)
    @Basic
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Basic
    @Column(name = "created_by")
    private Integer createdBy;

    @JsonIgnore
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }


    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ScreenSeat{" +
                "id=" + id +
                ", screenId=" + screenId +
                ", seatType=" + seatType +
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
        if (seatType != null ? !seatType.equals(that.seatType) : that.seatType != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        return !(createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (screenId != null ? screenId.hashCode() : 0);
        result = 31 * result + (seatType != null ? seatType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
