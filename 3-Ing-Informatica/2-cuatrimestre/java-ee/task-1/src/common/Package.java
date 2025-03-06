package src.common;

import java.io.Serializable;

public record Package(
        PackageType type,
        UserInfo user,
        String message
) implements Serializable {}

