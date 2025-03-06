package src.common.packages;

import src.common.UserInfo;

public class LoginPackage extends CustomPackage {
    public final UserInfo user;

    public LoginPackage(PackageType type, UserInfo user) {
        super(type);
        this.user = user;
    }
}
