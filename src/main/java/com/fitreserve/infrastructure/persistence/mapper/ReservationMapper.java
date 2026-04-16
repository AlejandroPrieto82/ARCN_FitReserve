package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.*;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.ReservationEntity;

public class ReservationMapper {

    public static ReservationEntity toEntity(Reservation r) {
        return new ReservationEntity(
                r.getId().getValue(),
                r.getUserId().getValue(),
                r.getClassId().getValue(),
                r.getStatus().name()
        );
    }

    public static Reservation toDomain(ReservationEntity e) {

        Reservation reservation = new Reservation(
                ReservationId.fromString(e.getId().toString()),
                UserId.fromString(e.getUserId().toString()),
                ClassId.fromString(e.getClassId().toString())
        );

        if ("CANCELLED".equals(e.getStatus())) {
            reservation.cancel();
        }

        return reservation;
    }
}