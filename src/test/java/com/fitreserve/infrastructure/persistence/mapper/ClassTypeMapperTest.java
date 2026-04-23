package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.ClassType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassTypeMapperTest {

    @Test
    void shouldMapAllStringTypes_WhenToDomainCalled() {

        assertAll(
                () -> assertEquals(ClassType.YOGA,       ClassTypeMapper.toDomain("YOGA")),
                () -> assertEquals(ClassType.CROSSFIT,   ClassTypeMapper.toDomain("CROSSFIT")),
                () -> assertEquals(ClassType.CARDIO,     ClassTypeMapper.toDomain("CARDIO")),
                () -> assertEquals(ClassType.WEIGHTS,    ClassTypeMapper.toDomain("WEIGHTS")),
                () -> assertEquals(ClassType.FUNCTIONAL, ClassTypeMapper.toDomain("FUNCTIONAL"))
        );
    }

    @Test
    void shouldMapAllEnumTypes_WhenToEntityCalled() {

        assertAll(
                () -> assertEquals("YOGA",       ClassTypeMapper.toEntity(ClassType.YOGA)),
                () -> assertEquals("CROSSFIT",   ClassTypeMapper.toEntity(ClassType.CROSSFIT)),
                () -> assertEquals("CARDIO",     ClassTypeMapper.toEntity(ClassType.CARDIO)),
                () -> assertEquals("WEIGHTS",    ClassTypeMapper.toEntity(ClassType.WEIGHTS)),
                () -> assertEquals("FUNCTIONAL", ClassTypeMapper.toEntity(ClassType.FUNCTIONAL))
        );
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenToDomainCalledWithInvalidType() {

        assertThrows(IllegalArgumentException.class, () -> ClassTypeMapper.toDomain("INVALID"));
    }

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        assertDoesNotThrow(ClassTypeMapper::new);
    }
}
