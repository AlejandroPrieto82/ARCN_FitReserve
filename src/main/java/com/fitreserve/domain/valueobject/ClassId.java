package com.fitreserve.domain.valueobject;

import java.util.UUID;

public class ClassId {

    private final UUID value;

    public ClassId(UUID value) {
        this.value = value;
    }

    public static ClassId fromString(String value) {
        return new ClassId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }
}