package com.fitreserve.shared.util;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UuidGeneratorTest {

    @Test
    void shouldGenerateNonNullUUID_WhenGenerateCalled() {

        UUID result = UuidGenerator.generate();

        assertNotNull(result);
    }

    @Test
    void shouldGenerateUniqueUUIDs_WhenCalledMultipleTimes() {

        UUID first  = UuidGenerator.generate();
        UUID second = UuidGenerator.generate();

        assertNotEquals(first, second);
    }

    @Test
    void shouldGenerateNonNullString_WhenGenerateStringCalled() {

        String result = UuidGenerator.generateString();

        assertNotNull(result);
    }

    @Test
    void shouldGenerateValidUUIDString_WhenGenerateStringCalled() {

        String result = UuidGenerator.generateString();

        assertDoesNotThrow(() -> UUID.fromString(result));
    }

    @Test
    void shouldGenerateUniqueStrings_WhenCalledMultipleTimes() {

        String first  = UuidGenerator.generateString();
        String second = UuidGenerator.generateString();

        assertNotEquals(first, second);
    }

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        UuidGenerator instance = new UuidGenerator();

        assertNotNull(instance);
    }
}
