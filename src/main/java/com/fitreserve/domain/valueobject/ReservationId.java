package com.fitreserve.domain.valueobject;

import java.util.UUID;

public class ReservationId {

    private final UUID value;

    public ReservationId(UUID value) {
        this.value = value;
    }

    public static ReservationId fromString(String value) {
        return new ReservationId(UUID.fromString(value));
    }

    public static ReservationId generate() {
        return new ReservationId(UUID.randomUUID());
    }

    public UUID getValue() {
        return value;
    }
}