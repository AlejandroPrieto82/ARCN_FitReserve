package com.fitreserve.domain.valueobject;

import java.util.UUID;

public class UserId {

    private final UUID value;

    public UserId(UUID value) {
        this.value = value;
    }

    public static UserId fromString(String value) {
        return new UserId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }
}