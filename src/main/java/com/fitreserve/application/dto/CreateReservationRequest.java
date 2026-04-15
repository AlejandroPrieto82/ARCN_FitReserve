package com.fitreserve.application.dto;

public class CreateReservationRequest {

	private final String userId;
	private final String sessionId;
	private final boolean userActive;
	private final boolean userAlreadyReservedSession;
	private final boolean sessionHasAvailableCapacity;

	public CreateReservationRequest(
			String userId,
			String sessionId,
			boolean userActive,
			boolean userAlreadyReservedSession,
			boolean sessionHasAvailableCapacity
	) {
		this.userId = userId;
		this.sessionId = sessionId;
		this.userActive = userActive;
		this.userAlreadyReservedSession = userAlreadyReservedSession;
		this.sessionHasAvailableCapacity = sessionHasAvailableCapacity;
	}

	public String getUserId() {
		return userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public boolean isUserAlreadyReservedSession() {
		return userAlreadyReservedSession;
	}

	public boolean isSessionHasAvailableCapacity() {
		return sessionHasAvailableCapacity;
	}
}
