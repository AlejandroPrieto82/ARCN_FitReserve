package com.fitreserve.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClassIdTest {

    @Test
    void shouldGenerateNonNullId_WhenGenerateCalled() {

        ClassId id = ClassId.generate();

        assertNotNull(id.getValue());
    }

    @Test
    void shouldGenerateUniqueIds_WhenCalledMultipleTimes() {

        ClassId id1 = ClassId.generate();
        ClassId id2 = ClassId.generate();

        assertNotEquals(id1.getValue(), id2.getValue());
    }

    @Test
    void shouldCreateFromUUID_WhenFromCalled() {

        UUID uuid = UUID.randomUUID();

        ClassId id = ClassId.from(uuid);

        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldCreateFromString_WhenValidUUIDStringProvided() {

        UUID uuid = UUID.randomUUID();

        ClassId id = ClassId.fromString(uuid.toString());

        assertEquals(uuid, id.getValue());
    }

    @Test
    void shouldReturnStringRepresentation_WhenValueMethodCalled() {

        UUID uuid = UUID.randomUUID();

        ClassId id = ClassId.from(uuid);

        assertEquals(uuid.toString(), id.value());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenFromStringWithInvalidUUID() {

        assertThrows(IllegalArgumentException.class, () -> ClassId.fromString("not-a-uuid"));
    }
}
