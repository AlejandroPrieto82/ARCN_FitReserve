package com.fitreserve.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class ReservationId {

    private final UUID value;

    public ReservationId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("ReservationId cannot be null");
        }
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationId)) return false;
        ReservationId that = (ReservationId) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}