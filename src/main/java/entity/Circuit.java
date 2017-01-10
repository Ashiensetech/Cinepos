package entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;


@Entity
@Table(name = "circuit")
public class Circuit {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Basic
    @Column(name = "site_code")
    private String siteCode;

    @Basic
    @Column(name = "site_name")
    private String siteName;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "city")
    private String 	city;

    @Basic
    @Column(name = "country")
    private String 	country;

    @Basic
    @Column(name = "website")
    private String 	website;

    @Basic
    @Column(name = "phone_no")
    private String 	phoneNo;

    @Basic
    @Column(name = "screen_no")
    private int screenNo;

    @Basic
    @Column(name = "booking_cancellation_time")
    private Time bookingCancellationTime;

    @Basic
    @Column(name = "refund_cancellation_time")
    private int refundCancellationTime;

    @Basic
    @Column(name = "created_by",nullable = true)
    private int createdBy;

    @Basic
    @Column(name = "updated_by",nullable = true)
    private int updatedBy;

    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    @Basic
    @Column(name = "updated_at",nullable = true)
    private Timestamp updatedAt;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(int screenNo) {
        this.screenNo = screenNo;
    }

    public Time getBookingCancellationTime() {
        return bookingCancellationTime;
    }

    public void setBookingCancellationTime(Time bookingCancellationTime) {
        this.bookingCancellationTime = bookingCancellationTime;
    }

    public int getRefundCancellationTime() {
        return refundCancellationTime;
    }

    public void setRefundCancellationTime(int refundCancellationTime) {
        this.refundCancellationTime = refundCancellationTime;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circuit circuit = (Circuit) o;

        if (Id != circuit.Id) return false;
        if (screenNo != circuit.screenNo) return false;
        if (refundCancellationTime != circuit.refundCancellationTime) return false;
        if (createdBy != circuit.createdBy) return false;
        if (updatedBy != circuit.updatedBy) return false;
        if (siteCode != null ? !siteCode.equals(circuit.siteCode) : circuit.siteCode != null) return false;
        if (siteName != null ? !siteName.equals(circuit.siteName) : circuit.siteName != null) return false;
        if (address != null ? !address.equals(circuit.address) : circuit.address != null) return false;
        if (city != null ? !city.equals(circuit.city) : circuit.city != null) return false;
        if (country != null ? !country.equals(circuit.country) : circuit.country != null) return false;
        if (website != null ? !website.equals(circuit.website) : circuit.website != null) return false;
        if (phoneNo != null ? !phoneNo.equals(circuit.phoneNo) : circuit.phoneNo != null) return false;
        if (bookingCancellationTime != null ? !bookingCancellationTime.equals(circuit.bookingCancellationTime) : circuit.bookingCancellationTime != null)
            return false;
        if (createdAt != null ? !createdAt.equals(circuit.createdAt) : circuit.createdAt != null) return false;
        return updatedAt != null ? updatedAt.equals(circuit.updatedAt) : circuit.updatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = Id;
        result = 31 * result + (siteCode != null ? siteCode.hashCode() : 0);
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (phoneNo != null ? phoneNo.hashCode() : 0);
        result = 31 * result + screenNo;
        result = 31 * result + (bookingCancellationTime != null ? bookingCancellationTime.hashCode() : 0);
        result = 31 * result + refundCancellationTime;
        result = 31 * result + createdBy;
        result = 31 * result + updatedBy;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Circuit{" +
                "Id=" + Id +
                ", siteCode='" + siteCode + '\'' +
                ", siteName='" + siteName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", website='" + website + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", screenNo=" + screenNo +
                ", bookingCancellationTime=" + bookingCancellationTime +
                ", refundCancellationTime=" + refundCancellationTime +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
