package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.*;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;

public class GymClassMapper {

    public static GymClassEntity toEntity(GymClass gymClass) {
        return new GymClassEntity(
                gymClass.getId().getValue(),
                gymClass.getName(),
                gymClass.getType().name(),
                gymClass.getTimeSlot().getStart(),
                gymClass.getTimeSlot().getEnd(),
                gymClass.getCapacity().getValue(),
                gymClass.getReserved()
        );
    }

    public static GymClass toDomain(GymClassEntity entity) {

        GymClass gymClass = new GymClass(
                ClassId.fromString(entity.getId().toString()),
                entity.getName(),
                ClassType.valueOf(entity.getType()),
                new TimeSlot(entity.getStartTime(), entity.getEndTime()),
                new Cupo(entity.getCapacity())
        );

        for (int i = 0; i < entity.getReserved(); i++) {
            gymClass.reserveSpot();
        }

        return gymClass;
    }
}