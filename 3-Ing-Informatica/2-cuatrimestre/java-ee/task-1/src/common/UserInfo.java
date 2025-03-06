package src.common;

import java.io.Serializable;

public record UserInfo(String id, String nick) implements Serializable {
}
