package com.fitreserve.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

    @Test
    void shouldGenerateNonNullId_WhenGenerateCalled() {

        UserId id = UserId.generate();

        assertNotNull(id.getValue());
    }

    @Test
    void shouldGenerateUniqueIds_WhenCalledMultipleTimes() {

        UserId id1 = UserId.generate();
        UserId id2 = UserId.generate();

        assertNotEquals(id1.getValue(), id2.getValue());
    }

    @Test
    void shouldCreateFromUUID_WhenFromCalled() {

        UUID uuid = UUID.randomUUID();

        UserId id = UserId.from(uuid);

        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldCreateFromString_WhenValidUUIDStringProvided() {

        UUID uuid = UUID.randomUUID();

        UserId id = UserId.fromString(uuid.toString());

        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldReturnStringRepresentation_WhenValueMethodCalled() {

        UUID uuid = UUID.randomUUID();

        UserId id = UserId.from(uuid);

        assertEquals(uuid.toString(), id.value());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenFromStringWithInvalidUUID() {

        assertThrows(IllegalArgumentException.class, () -> UserId.fromString("not-a-uuid"));
    }
}
