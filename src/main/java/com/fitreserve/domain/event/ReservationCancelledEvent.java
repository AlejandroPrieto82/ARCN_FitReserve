package com.fitreserve.domain.event;

public class ReservationCancelledEvent {

    private final String reservationId;

    public ReservationCancelledEvent(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationId() {
        return reservationId;
    }
}