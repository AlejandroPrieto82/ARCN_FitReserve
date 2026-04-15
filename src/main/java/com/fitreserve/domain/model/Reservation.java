package com.fitreserve.domain.model;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Reservation {

	private final String reservationId;
	private final String userId;
	private final String sessionId;
	private final LocalDateTime createdAt;
	private ReservationStatus status;

	private Reservation(
			String reservationId,
			String userId,
			String sessionId,
			ReservationStatus status,
			LocalDateTime createdAt
	) {
		this.reservationId = requireText(reservationId, "reservationId is required");
		this.userId = requireText(userId, "userId is required");
		this.sessionId = requireText(sessionId, "sessionId is required");
		this.status = Objects.requireNonNull(status, "status is required");
		this.createdAt = Objects.requireNonNull(createdAt, "createdAt is required");
	}

	public static Reservation create(
			String userId,
			String sessionId,
			boolean userIsActive,
			boolean userAlreadyReservedSession,
			boolean sessionHasAvailableCapacity
	) {
		validateUserIsActive(userIsActive);
		validateNoDuplicateReservation(userAlreadyReservedSession);
		validateSessionCapacity(sessionHasAvailableCapacity);

		return new Reservation(
				UUID.randomUUID().toString(),
				userId,
				sessionId,
				ReservationStatus.ACTIVE,
				LocalDateTime.now()
		);
	}

	public void cancel() {
		validateCanBeCancelled();
		this.status = ReservationStatus.CANCELLED;
	}

	public boolean isActive() {
		return ReservationStatus.ACTIVE.equals(status);
	}

	public boolean isCancelled() {
		return ReservationStatus.CANCELLED.equals(status);
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

	private static void validateUserIsActive(boolean userIsActive) {
		if (!userIsActive) {
			throw new BusinessException("Only active users can create a reservation");
		}
	}

	private static void validateNoDuplicateReservation(boolean userAlreadyReservedSession) {
		if (userAlreadyReservedSession) {
			throw new BusinessException("User already has a reservation for this session");
		}
	}

	private static void validateSessionCapacity(boolean sessionHasAvailableCapacity) {
		if (!sessionHasAvailableCapacity) {
			throw new BusinessException("Session has no available capacity");
		}
	}

	private void validateCanBeCancelled() {
		if (isCancelled()) {
			throw new BusinessException("Reservation is already cancelled");
		}
	}

	private static String requireText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new ValidationException(message);
		}
		return value;
	}
}
