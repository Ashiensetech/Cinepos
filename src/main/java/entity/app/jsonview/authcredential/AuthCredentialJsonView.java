package entity.app.jsonview.authcredential;

import entity.app.jsonview.user.UserInfJsonView;

/**
 * Created by mi on 2/14/17.
 */
public class AuthCredentialJsonView {
    public interface Basic {}
    public interface Summary extends Basic, UserInfJsonView.Summary {}
    public interface Details extends  Summary{}
}
