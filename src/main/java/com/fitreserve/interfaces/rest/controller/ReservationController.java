package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CancelReservationUseCase;
import com.fitreserve.application.usecase.CreateReservationUseCase;
import com.fitreserve.application.usecase.GetUserReservationsUseCase;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.shared.util.ApiResponse;
import com.fitreserve.interfaces.rest.request.CreateReservationRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservationUseCase;
    private final CancelReservationUseCase cancelReservationUseCase;
    private final GetUserReservationsUseCase getUserReservationsUseCase;

    public ReservationController(CreateReservationUseCase createReservationUseCase,
                                 CancelReservationUseCase cancelReservationUseCase,
                                 GetUserReservationsUseCase getUserReservationsUseCase) {
        this.createReservationUseCase = createReservationUseCase;
        this.cancelReservationUseCase = cancelReservationUseCase;
        this.getUserReservationsUseCase = getUserReservationsUseCase;
    }

    @PostMapping
    public ApiResponse<Reservation> create(@RequestBody CreateReservationRequest request) {

        Reservation reservation = createReservationUseCase.execute(
                request.getUserId(),
                request.getClassId()
        );

        return new ApiResponse<>(reservation);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> cancel(@PathVariable String id) {
        cancelReservationUseCase.execute(id);
        return new ApiResponse<>("Reservation cancelled");
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<Reservation>> getByUser(@PathVariable String userId) {
        return new ApiResponse<>(getUserReservationsUseCase.execute(userId));
    }
}