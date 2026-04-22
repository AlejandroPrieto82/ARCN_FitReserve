package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GymClassMapperTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    @Test
    void shouldConvertGymClassToEntity_WhenToEntityCalled() {

        GymClass gymClass = new GymClass(
                ClassId.generate(),
                "Yoga",
                ClassType.YOGA,
                new TimeSlot(START, END),
                new Cupo(10)
        );

        GymClassEntity entity = GymClassMapper.toEntity(gymClass);

        assertAll(
                () -> assertEquals(gymClass.getId().getValue(), entity.getId()),
                () -> assertEquals("Yoga", entity.getName()),
                () -> assertEquals("YOGA", entity.getType()),
                () -> assertEquals(START, entity.getStartTime()),
                () -> assertEquals(END, entity.getEndTime()),
                () -> assertEquals(10, entity.getCapacity()),
                () -> assertEquals(0, entity.getReserved())
        );
    }

    @Test
    void shouldConvertEntityToGymClass_WhenToDomainCalled() {

        UUID id = UUID.randomUUID();
        GymClassEntity entity = new GymClassEntity(id, "Yoga", "YOGA", START, END, 10, 0);

        GymClass gymClass = GymClassMapper.toDomain(entity);

        assertAll(
                () -> assertEquals(id, gymClass.getId().getValue()),
                () -> assertEquals("Yoga", gymClass.getName()),
                () -> assertEquals(ClassType.YOGA, gymClass.getType()),
                () -> assertEquals(0, gymClass.getReserved())
        );
    }

    @Test
    void shouldPreserveReservedCount_WhenToDomainCalledWithReservedSpots() {

        UUID id = UUID.randomUUID();
        GymClassEntity entity = new GymClassEntity(id, "Cardio", "CARDIO", START, END, 10, 3);

        GymClass gymClass = GymClassMapper.toDomain(entity);

        assertEquals(3, gymClass.getReserved());
    }

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        assertDoesNotThrow(() -> new GymClassMapper());
    }
}
