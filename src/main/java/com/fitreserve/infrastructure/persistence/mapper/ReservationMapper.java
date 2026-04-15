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
        return new Reservation(
                new ReservationId(e.getId()),
                new UserId(e.getUserId()),
                new ClassId(e.getClassId())
        );
    }
}