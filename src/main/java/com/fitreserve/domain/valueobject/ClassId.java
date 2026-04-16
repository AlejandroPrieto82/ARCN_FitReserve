package com.fitreserve.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class ClassId {

    private final UUID value;

    private ClassId(UUID value) {
        this.value = value;
    }

    public static ClassId generate() {
        return new ClassId(UUID.randomUUID());
    }

    public static ClassId from(UUID value) {
        return new ClassId(value);
    }

    public static ClassId fromString(String id) {
        return new ClassId(UUID.fromString(id));
    }

    public UUID getValue() {
        return value;
    }

    public String value() {
        return value.toString();
    }
}