package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.*;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.shared.util.ApiResponse;
import com.fitreserve.interfaces.rest.request.CreateReservationRequest;
import com.fitreserve.interfaces.rest.response.ReservationResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ApiResponse<ReservationResponse> create(@RequestBody CreateReservationRequest request) {

        Reservation reservation = createReservationUseCase.execute(
                request.getUserId(),
                request.getClassId()
        );

        return ApiResponse.of(ReservationResponse.from(reservation));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> cancel(@PathVariable String id) {
        cancelReservationUseCase.execute(id);
        return ApiResponse.of("Reservation cancelled");
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<ReservationResponse>> getByUser(@PathVariable String userId) {

        return ApiResponse.of(
                getUserReservationsUseCase.execute(userId)
                        .stream()
                        .map(ReservationResponse::from)
                        .toList()
        );
    }
}