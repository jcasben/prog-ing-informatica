package src.common.packages;

import src.common.UserInfo;

/**
 * Custom Package that transport a message from a user.
 *
 * @author jcasben
 */
public class MessagePackage extends CustomPackage {
    public final String message;
    public final UserInfo user;

    public MessagePackage(PackageType type, String message, UserInfo user) {
        super(type);
        this.message = message;
        this.user = user;
    }
}
