package entity.iface;

import com.fasterxml.jackson.annotation.JsonView;
import entity.ScreenDimension;
import entity.ScreenSeat;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mi on 2/10/17.
 */
public interface ScreenSummaryIfaceApp extends ScreenShortIfaceApp {


    @JsonView(ScreenSummaryIfaceApp.class)
    boolean getIsSeatPlanComplete();


    @JsonView(ScreenSummaryIfaceApp.class)
     int getNoOfSeat();

    @JsonView(ScreenSummaryIfaceApp.class)
     boolean isActive();

    @JsonView(ScreenSummaryIfaceApp.class)
     int getRowCount();

    @JsonView(ScreenSummaryIfaceApp.class)
     int getColumnCount();

    @JsonView(ScreenSummaryIfaceApp.class)
    Time getOpeningTime();

    @JsonView(ScreenSummaryIfaceApp.class)
    Time getClosingTime();


    @JsonView(ScreenSummaryIfaceApp.class)
     Timestamp getCreatedAt();
    
}

