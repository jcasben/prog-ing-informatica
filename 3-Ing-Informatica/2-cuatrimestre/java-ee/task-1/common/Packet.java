import java.io.Serializable;

public record Packet(
        PacketType type,
        String id,
        String nick,
        String message
) implements Serializable {}

enum PacketType implements Serializable {
    MESSAGE,
    LOGIN,
    LOGOUT
}
