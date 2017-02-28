package entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import entity.app.jsonview.screen.ScreenJsonView;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 1/5/17.
 */

@JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
@Entity
@Table(name = "screen", schema = "")
public class Screen{


    @JsonView(ScreenJsonView.Basic.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;



    @Basic
    @Column(name = "name")
    @JsonView(ScreenJsonView.Basic.class)
    private String name;

    @Basic
    @Column(name = "is_seat_plan_complete")
    @JsonView(ScreenJsonView.Basic.class)
    boolean isSeatPlanComplete;

    @Basic
    @Column(name = "no_of_seat")
    @JsonView(ScreenJsonView.Basic.class)
    private int noOfSeat;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screen_dimension_id",referencedColumnName = "id")
    @JsonView(ScreenJsonView.Summary.class)
    private ScreenDimension screenDimension;

    @Basic
    @Column(name = "active")
    @JsonView(ScreenJsonView.Summary.class)
    private boolean active;

    @Basic
    @Column(name = "row_count")
    @JsonView(ScreenJsonView.Summary.class)
    private int rowCount;


    @Basic
    @Column(name = "column_count")
    @JsonView(ScreenJsonView.Summary.class)
    private int columnCount;

    @Basic
    @Column(name = "opening_time")
    @JsonView(ScreenJsonView.Summary.class)
    private Time openingTime;

    @Basic
    @Column(name = "closing_time")
    @JsonView(ScreenJsonView.Summary.class)
    private Time closingTime;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id",referencedColumnName = "id",updatable = false,insertable = false)
    @JsonView(ScreenJsonView.Details.class)
    private List<ScreenSeat> seats;

    @Basic
    @Column(name = "created_by")
    @JsonView(ScreenJsonView.Summary.class)
    private Integer createdBy;

    @Basic
    @Column(name = "created_at")
    @JsonView(ScreenJsonView.Summary.class)
    private Timestamp createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean getIsSeatPlanComplete() {
        return isSeatPlanComplete;
    }

    public void setIsSeatPlanComplete(boolean isSeatPlanComplete) {
        this.isSeatPlanComplete = isSeatPlanComplete;
    }

    public int getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(int noOfSeat) {
        this.noOfSeat = noOfSeat;
    }


    public ScreenDimension getScreenDimension() {
        return screenDimension;
    }

    public void setScreenDimension(ScreenDimension screenDimension) {
        this.screenDimension = screenDimension;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public Time getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }

    public Time getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
    }


    public List<ScreenSeat> getSeats() {
        System.out.println("GET SEATS");
        System.out.println(seats);
        return (seats==null)?new ArrayList<>():seats;
    }

    public void setSeats(List<ScreenSeat> screenSeats) {
        this.seats = screenSeats;
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
    public String toString() {
        return "Screen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seatePlanComplete=" + isSeatPlanComplete +
                ", noOfSeat=" + noOfSeat +
                ", screenDimension=" + screenDimension +
                ", active=" + active +
                ", rowCount=" + rowCount +
                ", columnCount=" + columnCount +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", seats=" + seats +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Screen screen = (Screen) o;

        if (id != screen.id) return false;
        if (isSeatPlanComplete != screen.isSeatPlanComplete) return false;
        if (noOfSeat != screen.noOfSeat) return false;
        if (active != screen.active) return false;
        if (rowCount != screen.rowCount) return false;
        if (columnCount != screen.columnCount) return false;
        if (name != null ? !name.equals(screen.name) : screen.name != null) return false;
        if (screenDimension != null ? !screenDimension.equals(screen.screenDimension) : screen.screenDimension != null)
            return false;
        if (openingTime != null ? !openingTime.equals(screen.openingTime) : screen.openingTime != null) return false;
        if (closingTime != null ? !closingTime.equals(screen.closingTime) : screen.closingTime != null) return false;
        if (seats != null ? !seats.equals(screen.seats) : screen.seats != null) return false;
        if (createdBy != null ? !createdBy.equals(screen.createdBy) : screen.createdBy != null) return false;
        return !(createdAt != null ? !createdAt.equals(screen.createdAt) : screen.createdAt != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isSeatPlanComplete ? 1 : 0);
        result = 31 * result + noOfSeat;
        result = 31 * result + (screenDimension != null ? screenDimension.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + rowCount;
        result = 31 * result + columnCount;
        result = 31 * result + (openingTime != null ? openingTime.hashCode() : 0);
        result = 31 * result + (closingTime != null ? closingTime.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
