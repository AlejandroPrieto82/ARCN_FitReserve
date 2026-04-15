package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.dto.CreateReservationRequest;
import com.fitreserve.application.dto.CreateReservationResponse;
import com.fitreserve.application.usecase.CreateReservationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private final CreateReservationUseCase createReservationUseCase;

	public ReservationController(CreateReservationUseCase createReservationUseCase) {
		this.createReservationUseCase = createReservationUseCase;
	}

	@PostMapping
	public ResponseEntity<CreateReservationResponse> createReservation(@RequestBody CreateReservationRequest request) {
		CreateReservationResponse response = createReservationUseCase.execute(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
