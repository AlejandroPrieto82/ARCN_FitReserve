package com.fitreserve.domain.event;

public class ReservationCreatedEvent {

    private final String reservationId;
    private final String userId;
    private final String classId;

    public ReservationCreatedEvent(String reservationId, String userId, String classId) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.classId = classId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getClassId() {
        return classId;
    }
}