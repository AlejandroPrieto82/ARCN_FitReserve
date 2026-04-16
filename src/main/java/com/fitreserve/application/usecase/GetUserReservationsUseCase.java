package com.fitreserve.application.usecase;

import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.domain.model.Reservation;

import java.util.List;

public class GetUserReservationsUseCase {

    private final ReservationRepository repository;

    public GetUserReservationsUseCase(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> execute(String userIdRaw) {

        return repository.findByUserId(
                UserId.fromString(userIdRaw)
        );
    }
}