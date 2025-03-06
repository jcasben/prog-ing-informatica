package src.common.packages;

import src.common.UserInfo;

/**
 * Custom Package that transport an array with the connected users' information.
 *
 * @author jcasben
 */
public class UserListPackage extends CustomPackage {
    public final UserInfo[] connectedUsers;

    public UserListPackage(PackageType type, UserInfo[] connectedUsers) {
        super(type);
        this.connectedUsers = connectedUsers;
    }
}
