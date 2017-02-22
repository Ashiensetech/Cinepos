package entity.app.jsonview.concession.product;

/**
 * Created by mi on 2/22/17.
 */
public class ConcessionProductJsonView {
    public interface Basic {}
    public interface Summary extends Basic, ConcessionProductCategoryJsonView.Basic{}
    public interface Details extends  Summary{}
}
