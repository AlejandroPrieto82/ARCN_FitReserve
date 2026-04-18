package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.ReservationId;

public class CancelReservationUseCase {

    private final ReservationRepository repository;
    private final GymClassRepository classRepository;

    public CancelReservationUseCase(ReservationRepository repository,
            GymClassRepository classRepository) {
        this.repository = repository;
        this.classRepository = classRepository;
    }

    public void execute(String reservationId) {

        Reservation reservation = repository.findById(
                ReservationId.fromString(reservationId)
        ).orElseThrow(() -> new NotFoundException("Reservation not found"));

        reservation.cancel();

        GymClass gymClass = classRepository.findById(
            ClassId.from(reservation.getClassId().getValue())
        ).orElseThrow(() -> new NotFoundException("Class not found"));

        gymClass.unreserveSpot();

        repository.save(reservation);
        classRepository.save(gymClass);
    }
}