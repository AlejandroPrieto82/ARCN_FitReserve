package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.ReservationId;
import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.interfaces.rest.request.CreateReservationRequest;
import com.fitreserve.interfaces.rest.response.CreateReservationResponse;

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

    public CreateReservationResponse execute(CreateReservationRequest request) {

        UserId userId = UserId.fromString(request.getUserId());
        ClassId classId = ClassId.fromString(request.getClassId());

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

        return new CreateReservationResponse(
                reservation.getId().getValue().toString(),
                "Reservation created"
        );
    }
}