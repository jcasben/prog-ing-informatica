package src.common.packages;

import java.io.Serializable;

/**
 * Different types of Packages that can be sent.
 *
 * @author jcasben
 */
public enum PackageType implements Serializable {
    MESSAGE,
    LOGIN,
    LOGOUT,
    USER_LIST
}
