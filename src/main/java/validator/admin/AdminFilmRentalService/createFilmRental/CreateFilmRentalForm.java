package validator.admin.AdminFilmRentalService.createFilmRental;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by sunno on 1/23/17.
 */
public class CreateFilmRentalForm {


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getFormattedStartDate() {
        return formattedStartDate;
    }

    public void setFormattedStartDate(Date formattedStartDate) {
        this.formattedStartDate = formattedStartDate;
    }

    public Date getFormattedEndDate() {
        return formattedEndDate;
    }

    public void setFormattedEndDate(Date formattedEndDate) {
        this.formattedEndDate = formattedEndDate;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    @NotNull(message = "Film is required")
    private Integer filmId;

    @NotNull(message = "Start Date is required")
    private String startDate;

    @NotNull(message = "End Date is required")
    private String endDate;

    private Date formattedStartDate;

    private Date formattedEndDate;

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    @NotEmpty(message = "Week Name is required")
    private String weekName;


    @Override
    public String toString() {
        return "CreateFilmForm{" +
                "filmId='" + filmId +
                ", weekName=" + weekName +
                ", startDate=" + startDate +
                ", endDate='" + endDate +
                ", formattedStartDate=" + formattedStartDate +
                ", formattedEndDate=" + formattedEndDate +
                '}';
    }

}
