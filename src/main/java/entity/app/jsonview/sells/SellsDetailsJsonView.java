package entity.app.jsonview.sells;

import entity.app.jsonview.combo.ComboJsonView;
import entity.app.jsonview.concession.product.ConcessionProductJsonView;
import entity.app.jsonview.terminal.TerminalJsonView;
import entity.app.jsonview.ticket.TicketJsonView;

/**
 * Created by mi on 2/22/17.
 */
public class SellsDetailsJsonView {
    public interface Basic {}
    public interface Summary extends Basic,
            ConcessionProductJsonView.Summary,
            TicketJsonView.Summary,
            TerminalJsonView.Summary,
            ComboJsonView.Summary{}
    public interface Details extends  Summary{}
}
