package validator.admin.AdminCircuitService.createCircuit;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.sql.Time;

/**
 * Created by Sarwar on 1/10/2017.
 */
public class createCircuitForm {

    private Integer Id;

    @NotBlank(message = "Site name are required")
    @Length(max = 55,message = "Site name too large")
    private String siteName;

    @NotBlank(message = "Address are required")
    @Length(max = 255,message = "Address too large")
    private String address;

    @NotBlank(message = "City are required")
    private String 	city;

    @NotBlank(message = "Country are required")
    private String 	country;

    @NotBlank(message = "Website are required")
    @Pattern(regexp="^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$",message = "Website validation failed.")
    private String 	webSite;

    @NotBlank(message = "Phone no are required")
    @Length(max = 30,message = "Phone is too large")
    private String 	phoneNo;

    @NotNull(message = "The minimum screen no required")
    @Min(value = 1, message = "The minimum screen no should be minimum 1")
    private Integer screenNo;


    @NotNull(message = "Booking cancellation time required")
    private Time bookingCancellationTime;

    @NotNull(message = "The refund deduction are required")
    @DecimalMax(value = "99999.99", message = "The refund deduction can not be more than 99999.999 ")
    @DecimalMin(value = "1.00", message = "The refund deduction can not be less than 1.00 digit ")
    private Float refundDeductionPercentage;

    @NotNull(message = "Refund cancellation time required")
    private Time refundCancellationTime;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Integer getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(Integer screenNo) {
        this.screenNo = screenNo;
    }

    public Time getBookingCancellationTime() {
        return bookingCancellationTime;
    }

    public void setBookingCancellationTime(Time bookingCancellationTime) {
        this.bookingCancellationTime = bookingCancellationTime;
    }

    public Float getRefundDeductionPercentage() {
        return refundDeductionPercentage;
    }

    public void setRefundDeductionPercentage(Float refundDeductionPercentage) {
        this.refundDeductionPercentage = refundDeductionPercentage;
    }

    public Time getRefundCancellationTime() {
        return refundCancellationTime;
    }

    public void setRefundCancellationTime(Time refundCancellationTime) {
        this.refundCancellationTime = refundCancellationTime;
    }

    @Override
    public String toString() {
        return "createCircuitForm{" +
                "Id=" + Id +
                ", siteName='" + siteName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", webSite='" + webSite + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", screenNo=" + screenNo +
                ", bookingCancellationTime=" + bookingCancellationTime +
                ", refundDeductionPercentage=" + refundDeductionPercentage +
                ", refundCancellationTime=" + refundCancellationTime +
                '}';
    }
}
