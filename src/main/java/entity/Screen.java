package entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by mi on 1/5/17.
 */
@Entity
@Table(name = "screen", schema = "", catalog = "cinepos")
public class Screen {
    private int id;
    private String screenName;
    private int noOfSeat;
    private String screenType;
    private byte active;
    private int rowNumber;
    private int columnNumber;
    private Time opening;
    private Time closingTime;
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
    @Column(name = "screen_name")
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Basic
    @Column(name = "no_of_seat")
    public int getNoOfSeat() {
        return noOfSeat;
    }

    public void setNoOfSeat(int noOfSeat) {
        this.noOfSeat = noOfSeat;
    }

    @Basic
    @Column(name = "screen_type")
    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    @Basic
    @Column(name = "active")
    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Basic
    @Column(name = "row_number")
    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    @Basic
    @Column(name = "column_number")
    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    @Basic
    @Column(name = "opening")
    public Time getOpening() {
        return opening;
    }

    public void setOpening(Time opening) {
        this.opening = opening;
    }

    @Basic
    @Column(name = "closing_time")
    public Time getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Screen that = (Screen) o;

        if (id != that.id) return false;
        if (noOfSeat != that.noOfSeat) return false;
        if (active != that.active) return false;
        if (rowNumber != that.rowNumber) return false;
        if (columnNumber != that.columnNumber) return false;
        if (screenName != null ? !screenName.equals(that.screenName) : that.screenName != null) return false;
        if (screenType != null ? !screenType.equals(that.screenType) : that.screenType != null) return false;
        if (opening != null ? !opening.equals(that.opening) : that.opening != null) return false;
        if (closingTime != null ? !closingTime.equals(that.closingTime) : that.closingTime != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + noOfSeat;
        result = 31 * result + (screenType != null ? screenType.hashCode() : 0);
        result = 31 * result + (int) active;
        result = 31 * result + rowNumber;
        result = 31 * result + columnNumber;
        result = 31 * result + (opening != null ? opening.hashCode() : 0);
        result = 31 * result + (closingTime != null ? closingTime.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
