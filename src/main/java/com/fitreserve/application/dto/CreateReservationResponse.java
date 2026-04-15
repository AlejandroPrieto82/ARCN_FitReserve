package com.fitreserve.application.dto;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.ReservationStatus;

import java.time.LocalDateTime;

public class CreateReservationResponse {

    private final String reservationId;
    private final String userId;
    private final String sessionId;
    private final ReservationStatus status;
    private final LocalDateTime createdAt;

    public CreateReservationResponse(
            String reservationId,
            String userId,
            String sessionId,
            ReservationStatus status,
            LocalDateTime createdAt
    ) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.sessionId = sessionId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static CreateReservationResponse from(Reservation reservation) {
        return new CreateReservationResponse(
                reservation.getReservationId(),
                reservation.getUserId(),
                reservation.getSessionId(),
                reservation.getStatus(),
                reservation.getCreatedAt()
        );
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
