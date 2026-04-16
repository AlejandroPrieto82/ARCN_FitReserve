package com.fitreserve.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class UserId {

    private final UUID value;

    private UserId(UUID value) {
        this.value = value;
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId from(UUID value) {
        return new UserId(value);
    }

    public static UserId fromString(String id) {
        return new UserId(UUID.fromString(id));
    }

    public UUID getValue() {
        return value;
    }

    public String value() {
        return value.toString();
    }
}