package com.fitreserve.domain.valueobject;

public class Cupo {

    private final int value;

    public Cupo(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Cupo must be greater than 0");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isFull(int currentReservations) {
        return currentReservations >= value;
    }
}