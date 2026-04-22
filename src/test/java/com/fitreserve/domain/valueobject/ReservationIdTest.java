package com.fitreserve.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationIdTest {

    @Test
    void shouldGenerateNonNullId_WhenGenerateCalled() {

        ReservationId id = ReservationId.generate();

        assertNotNull(id.getValue());
    }

    @Test
    void shouldGenerateUniqueIds_WhenCalledMultipleTimes() {

        ReservationId id1 = ReservationId.generate();
        ReservationId id2 = ReservationId.generate();

        assertNotEquals(id1.getValue(), id2.getValue());
    }

    @Test
    void shouldCreateFromUUID_WhenFromCalled() {

        UUID uuid = UUID.randomUUID();

        ReservationId id = ReservationId.from(uuid);

        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldCreateFromString_WhenValidUUIDStringProvided() {

        UUID uuid = UUID.randomUUID();

        ReservationId id = ReservationId.fromString(uuid.toString());

        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldReturnStringRepresentation_WhenValueMethodCalled() {

        UUID uuid = UUID.randomUUID();

        ReservationId id = ReservationId.from(uuid);

        assertEquals(uuid.toString(), id.value());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenFromStringWithInvalidUUID() {

        assertThrows(IllegalArgumentException.class, () -> ReservationId.fromString("not-a-uuid"));
    }
}
