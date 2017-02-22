package entity.app.jsonview.combo;

/**
 * Created by mi on 2/22/17.
 */
public class ComboJsonView {
    public interface Basic {}
    public interface Summary extends Basic,ComboDetailsJsonView.Summary{}
    public interface Details extends  Summary{}
}
