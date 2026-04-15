package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.ReservationStatus;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.ReservationEntity;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toDomain(ReservationEntity entity) {
        Reservation reservation = new Reservation(
                new ReservationId(entity.getId()),
                new UserId(entity.getUserId()),
                new ClassId(entity.getClassId())
        );

        if ("CANCELLED".equals(entity.getStatus())) {
            reservation.cancel();
        }

        return reservation;
    }

    public ReservationEntity toEntity(Reservation reservation) {
        return new ReservationEntity(
                reservation.getId().getValue(),
                reservation.getUserId().getValue(),
                reservation.getClassId().getValue(),
                reservation.getStatus().name()
        );
    }
}