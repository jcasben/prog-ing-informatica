package src.common;

import java.io.Serializable;

public record Packet(
        PacketType type,
        String id,
        String nick,
        String message
) implements Serializable {}

