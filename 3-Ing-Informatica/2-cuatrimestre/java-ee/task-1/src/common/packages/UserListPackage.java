package src.common.packages;

import src.common.UserInfo;

public class UserListPackage extends CustomPackage {
    public final UserInfo [] connectedUsers;

    public UserListPackage(PackageType type, UserInfo[] connectedUsers) {
        super(type);
        this.connectedUsers = connectedUsers;
    }
}
