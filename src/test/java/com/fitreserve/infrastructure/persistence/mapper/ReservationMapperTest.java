package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.ReservationStatus;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.ReservationEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTest {

    @Test
    void shouldConvertReservationToEntity_WhenToEntityCalled() {

        Reservation reservation = new Reservation(
                ReservationId.generate(),
                UserId.generate(),
                ClassId.generate()
        );

        ReservationEntity entity = ReservationMapper.toEntity(reservation);

        assertAll(
                () -> assertEquals(reservation.getId().getValue(), entity.getId()),
                () -> assertEquals(reservation.getUserId().getValue(), entity.getUserId()),
                () -> assertEquals(reservation.getClassId().getValue(), entity.getClassId()),
                () -> assertEquals("ACTIVE", entity.getStatus())
        );
    }

    @Test
    void shouldConvertEntityToActiveReservation_WhenToDomainCalledWithActiveStatus() {

        UUID id      = UUID.randomUUID();
        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        ReservationEntity entity = new ReservationEntity(id, userId, classId, "ACTIVE");

        Reservation reservation = ReservationMapper.toDomain(entity);

        assertAll(
                () -> assertEquals(id, reservation.getId().getValue()),
                () -> assertEquals(userId, reservation.getUserId().getValue()),
                () -> assertEquals(classId, reservation.getClassId().getValue()),
                () -> assertEquals(ReservationStatus.ACTIVE, reservation.getStatus())
        );
    }

    @Test
    void shouldCancelReservation_WhenToDomainCalledWithCancelledStatus() {

        UUID id      = UUID.randomUUID();
        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        ReservationEntity entity = new ReservationEntity(id, userId, classId, "CANCELLED");

        Reservation reservation = ReservationMapper.toDomain(entity);

        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        assertDoesNotThrow(() -> new ReservationMapper());
    }
}
