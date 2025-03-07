package src.common.packages;

import java.io.Serializable;

/**
 * Custom class that represents a Package that goes through the socket network.
 *
 * @author jcasben
 */
public class CustomPackage implements Serializable {
    public final PackageType type;

    public CustomPackage(PackageType type) {
        this.type = type;
    }
}
