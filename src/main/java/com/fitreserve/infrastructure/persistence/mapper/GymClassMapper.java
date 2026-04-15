package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;
import org.springframework.stereotype.Component;

@Component
public class GymClassMapper {

    public GymClass toDomain(GymClassEntity entity) {
        return new GymClass(
                new ClassId(entity.getId()),
                entity.getName(),
                ClassType.valueOf(entity.getType()),
                new TimeSlot(entity.getStartTime(), entity.getEndTime()),
                new Cupo(entity.getCapacity())
        );
    }

    public GymClassEntity toEntity(GymClass gymClass) {
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
}