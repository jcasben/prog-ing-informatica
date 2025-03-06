package src.common;

import java.io.Serializable;

/**
 * Record for storing the basic information about a client user.
 *
 * @param id   user id generated at client start.
 * @param nick nickname selected by the user ("Anonymous" if no nickname selected).
 * @author jcasben
 */
public record UserInfo(String id, String nick) implements Serializable {
}
