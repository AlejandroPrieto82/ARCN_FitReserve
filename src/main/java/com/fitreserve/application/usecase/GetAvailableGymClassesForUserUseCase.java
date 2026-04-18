package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.ReservationStatus;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.UserId;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class GetAvailableGymClassesForUserUseCase {

    private final GymClassRepository classRepository;
    private final ReservationRepository reservationRepository;

    public GetAvailableGymClassesForUserUseCase(
            GymClassRepository classRepository,
            ReservationRepository reservationRepository) {
        this.classRepository = classRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<GymClass> execute(String userIdRaw) {

        UserId userId = UserId.fromString(userIdRaw);

        Set<UUID> reservedClassIds = reservationRepository.findByUserId(userId)
                .stream()
                .filter(reservation -> reservation.getStatus() == ReservationStatus.ACTIVE)
                .map(reservation -> reservation.getClassId().getValue())
                .collect(java.util.stream.Collectors.toSet());

        return classRepository.findAll()
                .stream()
                .filter(gymClass -> !reservedClassIds.contains(gymClass.getId().getValue()))
                .toList();
    }
}