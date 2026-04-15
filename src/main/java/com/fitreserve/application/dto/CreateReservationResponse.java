package com.fitreserve.application.dto;

public class CreateReservationResponse {

    private final String reservationId;
    private final String message;

    public CreateReservationResponse(String reservationId, String message) {
        this.reservationId = reservationId;
        this.message = message;
    }

    public String getReservationId() { return reservationId; }
    public String getMessage() { return message; }
}