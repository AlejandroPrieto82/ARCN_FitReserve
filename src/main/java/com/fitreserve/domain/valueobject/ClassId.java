package com.fitreserve.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class ClassId {

    private final UUID value;

    public ClassId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("ClassId cannot be null");
        }
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassId)) return false;
        ClassId classId = (ClassId) o;
        return value.equals(classId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}