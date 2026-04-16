package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.model.*;
import com.fitreserve.domain.repository.*;
import com.fitreserve.domain.valueobject.*;

public class CreateReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final GymClassRepository classRepository;

    public CreateReservationUseCase(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            GymClassRepository classRepository) {

        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.classRepository = classRepository;
    }

    public Reservation execute(String userIdRaw, String classIdRaw) {

        UserId userId = UserId.fromString(userIdRaw);
        ClassId classId = ClassId.fromString(classIdRaw);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.isActive()) {
            throw new BusinessException("User inactive");
        }

        GymClass gymClass = classRepository.findById(classId)
                .orElseThrow(() -> new NotFoundException("Class not found"));

        if (!gymClass.hasAvailableSpots()) {
            throw new BusinessException("Class full");
        }

        if (reservationRepository.existsByUserIdAndClassId(userId, classId)) {
            throw new BusinessException("Already reserved");
        }

        Reservation reservation = new Reservation(
                ReservationId.generate(),
                userId,
                classId
        );

        gymClass.reserveSpot();

        reservationRepository.save(reservation);
        classRepository.save(gymClass);

        return reservation;
    }
}