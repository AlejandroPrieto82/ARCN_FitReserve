package com.fitreserve.application.usecase;

import com.fitreserve.application.dto.CreateReservationRequest;
import com.fitreserve.application.dto.CreateReservationResponse;
import com.fitreserve.domain.exception.ValidationException;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.ReservationRepository;

public class CreateReservationUseCase {

	private final ReservationRepository reservationRepository;

	public CreateReservationUseCase(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public CreateReservationResponse execute(CreateReservationRequest request) {
		validateRequest(request);

		Reservation reservation = Reservation.create(
				request.getUserId(),
				request.getSessionId(),
				request.isUserActive(),
				request.isUserAlreadyReservedSession(),
				request.isSessionHasAvailableCapacity()
		);

		Reservation savedReservation = reservationRepository.save(reservation);
		return CreateReservationResponse.from(savedReservation);
	}

	private void validateRequest(CreateReservationRequest request) {
		if (request == null) {
			throw new ValidationException("CreateReservationRequest is required");
		}
		validateText(request.getUserId(), "userId is required");
		validateText(request.getSessionId(), "sessionId is required");
	}

	private void validateText(String value, String message) {
		if (value == null || value.isBlank()) {
			throw new ValidationException(message);
		}
	}
}
