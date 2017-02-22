package entity.app.jsonview.sells;

import entity.app.jsonview.authcredential.AuthCredentialJsonView;
import entity.app.jsonview.screen.ScreenJsonView;

/**
 * Created by mi on 2/22/17.
 */
public class SellsJsonView {
    public interface Basic {}
    public interface Summary extends
            Basic,
            ScreenJsonView.Basic,
            SellsDetailsJsonView.Summary,
            AuthCredentialJsonView.Basic{}
    public interface Details extends  Summary{}
}
