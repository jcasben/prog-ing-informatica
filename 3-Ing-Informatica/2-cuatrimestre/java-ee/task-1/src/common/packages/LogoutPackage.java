package src.common.packages;

import src.common.UserInfo;

public class LogoutPackage extends CustomPackage {
    public final UserInfo user;

    public LogoutPackage(PackageType type, UserInfo user) {
        super(type);
        this.user = user;
    }
}
