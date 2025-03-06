package src.common.packages;

import java.io.Serializable;

public class CustomPackage implements Serializable {
    public final PackageType type;

    public CustomPackage(PackageType type) {
        this.type = type;
    }
}
