package com.fitreserve.domain.valueobject;

import java.util.UUID;
public class ReservationId {

    private final UUID value;

    private ReservationId(UUID value) {
        this.value = value;
    }

    public static ReservationId generate() {
        return new ReservationId(UUID.randomUUID());
    }

    public static ReservationId from(UUID value) {
        return new ReservationId(value);
    }

    public static ReservationId fromString(String value) {
        return new ReservationId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    public String value() {
        return value.toString();
    }
}