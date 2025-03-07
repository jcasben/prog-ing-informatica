package src.common.packages;

import src.common.UserInfo;

/**
 * Custom Package used to notify when a new user connects.
 *
 * @author jcasben
 */
public class LoginPackage extends CustomPackage {
    public final UserInfo user;

    public LoginPackage(PackageType type, UserInfo user) {
        super(type);
        this.user = user;
    }
}
