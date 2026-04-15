package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.ReservationId;

public class CancelReservationUseCase {

    private final ReservationRepository repository;

    public CancelReservationUseCase(ReservationRepository repository) {
        this.repository = repository;
    }

    public void execute(String reservationId) {

        Reservation reservation = repository.findById(
                ReservationId.fromString(reservationId)
        ).orElseThrow(() -> new NotFoundException("Reservation not found"));

        reservation.cancel();

        repository.save(reservation);
    }
}