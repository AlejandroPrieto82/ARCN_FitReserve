package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.UserId;

import java.util.List;

public class GetUserReservationsUseCase {

    private final ReservationRepository repository;

    public GetUserReservationsUseCase(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> execute(String userId) {
        return repository.findByUserId(UserId.fromString(userId));
    }
}