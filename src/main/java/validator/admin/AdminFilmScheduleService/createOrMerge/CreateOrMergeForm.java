package validator.admin.AdminFilmScheduleService.createOrMerge;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by mi on 1/24/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrMergeForm {

    Integer screenId;

    @NotBlank(message = "scheduleJson can't be empty")
    String scheduleJson;

    public ScheduleForm scheduleForm;

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getScheduleJson() {
        return scheduleJson;
    }

    public void setScheduleJson(String scheduleJson) {
        this.scheduleJson = scheduleJson;
    }

    @Override
    public String toString() {
        return "CreateOrMergeForm{" +
                ", scheduleForm=" + scheduleForm +
                '}';
    }
}
