package entity.iface;

import com.fasterxml.jackson.annotation.JsonView;
import entity.ScreenDimension;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by mi on 2/10/17.
 */
public interface ScreenShortIfaceApp {
    @JsonView(ScreenShortIfaceApp.class)
    int getId();
    @JsonView(ScreenShortIfaceApp.class)
    String getName();

}

