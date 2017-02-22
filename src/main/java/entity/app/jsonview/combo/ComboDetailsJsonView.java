package entity.app.jsonview.combo;

import entity.app.jsonview.concession.product.ConcessionProductJsonView;

/**
 * Created by mi on 2/22/17.
 */
public class ComboDetailsJsonView {
    public interface Basic {}
    public interface Summary extends Basic,ConcessionProductJsonView.Summary{}
    public interface Details extends  Summary{}
}
