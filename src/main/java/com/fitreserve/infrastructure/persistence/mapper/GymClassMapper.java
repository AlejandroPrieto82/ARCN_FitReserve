package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.*;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;

import java.util.UUID;

public class GymClassMapper {

    public static GymClassEntity toEntity(GymClass gymClass) {
        return new GymClassEntity(
                gymClass.getId().getValue(),
                gymClass.getName(),
                gymClass.getType().name(),
                gymClass.getTimeSlot().getStart(),
                gymClass.getTimeSlot().getEnd(),
                gymClass.getCapacity().getValue(),
                0
        );
    }

    public static GymClass toDomain(GymClassEntity entity) {
        return new GymClass(
                new ClassId(entity.getId()),
                entity.getName(),
                ClassType.valueOf(entity.getType()),
                new TimeSlot(entity.getStartTime(), entity.getEndTime()),
                new Cupo(entity.getCapacity())
        );
    }
}