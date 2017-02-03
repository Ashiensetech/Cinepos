package entity.jspView;

import entity.ScreenSeat;

/**
 * Created by mi on 2/2/17.
 */
public class TicketSeat extends ScreenSeat {
    String currentState ;

    public TicketSeat() {
    }
    public TicketSeat(ScreenSeat screenSeat) {
        this.setId(screenSeat.getId());
        this.setName(screenSeat.getName());
        this.setScreenId(screenSeat.getScreenId());
        this.setSeatType(screenSeat.getSeatType());
        this.setCreatedBy(screenSeat.getCreatedBy());
        this.setCreatedAt(screenSeat.getCreatedAt());

    }
    public TicketSeat(ScreenSeat screenSeat,String currentState) {
        this(screenSeat);
        this.setCurrentState(currentState);
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    @Override
    public String toString() {
        return "TicketSeat{" +
                "currentState='" + currentState + '\'' +
                "} " + super.toString();
    }
}
