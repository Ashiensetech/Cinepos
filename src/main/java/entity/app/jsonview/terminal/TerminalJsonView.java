package entity.app.jsonview.terminal;

import entity.app.jsonview.screen.ScreenJsonView;
import entity.app.jsonview.sells.SellsDetailsJsonView;

/**
 * Created by mi on 2/22/17.
 */
public class TerminalJsonView {
    public interface Basic {}
    public interface Summary extends Basic{}
    public interface Details extends  Summary{}
}
