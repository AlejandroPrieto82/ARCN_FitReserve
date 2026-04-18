package com.fitreserve.domain.repository;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.ReservationId;
import com.fitreserve.domain.valueobject.UserId;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(ReservationId reservationId);

    List<Reservation> findByUserId(UserId userId);

    boolean existsByUserIdAndClassId(UserId userId, ClassId classId);

    boolean existsActiveByUserIdAndClassId(UserId userId, ClassId classId);
}