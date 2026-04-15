package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.*;

import java.util.UUID;

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

    public Reservation execute(String userId, String classId) {

        UserId uid = UserId.fromString(userId);
        ClassId cid = ClassId.fromString(classId);

        User user = userRepository.findById(uid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.isActive()) {
            throw new BusinessException("User inactive");
        }

        GymClass gymClass = classRepository.findById(cid)
                .orElseThrow(() -> new NotFoundException("Class not found"));

        if (!gymClass.hasAvailableSpots()) {
            throw new BusinessException("Class full");
        }

        if (reservationRepository.existsByUserIdAndClassId(uid, cid)) {
            throw new BusinessException("Already reserved");
        }

        Reservation reservation = new Reservation(
                new ReservationId(UUID.randomUUID()),
                uid,
                cid
        );

        gymClass.reserveSpot();

        reservationRepository.save(reservation);
        classRepository.save(gymClass);

        return reservation;
    }
}