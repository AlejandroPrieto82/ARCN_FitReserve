package com.fitreserve.infrastructure.persistence.mapper;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DomainMapperTest {

    @Test
    void shouldConvertStringToUUID_WhenToUUIDCalled() {

        UUID uuid = UUID.randomUUID();

        assertEquals(uuid, DomainMapper.toUUID(uuid.toString()));
    }

    @Test
    void shouldConvertUUIDToString_WhenToStringCalled() {

        UUID uuid = UUID.randomUUID();

        assertEquals(uuid.toString(), DomainMapper.toString(uuid));
    }

    @Test
    void shouldRoundTrip_WhenConvertingStringToUUIDAndBack() {

        UUID original = UUID.randomUUID();

        assertEquals(original, DomainMapper.toUUID(DomainMapper.toString(original)));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenToUUIDCalledWithInvalidString() {

        assertThrows(IllegalArgumentException.class, () -> DomainMapper.toUUID("not-a-uuid"));
    }

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        assertDoesNotThrow(DomainMapper::new);
    }
}
