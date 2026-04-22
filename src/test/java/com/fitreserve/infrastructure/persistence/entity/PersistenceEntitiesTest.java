package com.fitreserve.infrastructure.persistence.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceEntitiesTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    // ── GymClassEntity ──────────────────────────────────────────────────────────

    @Test
    void shouldStoreAllFields_WhenGymClassEntityCreatedWithAllArgs() {

        UUID id = UUID.randomUUID();
        GymClassEntity entity = new GymClassEntity(id, "Yoga", "YOGA", START, END, 10, 3);

        assertAll(
                () -> assertEquals(id, entity.getId()),
                () -> assertEquals("Yoga", entity.getName()),
                () -> assertEquals("YOGA", entity.getType()),
                () -> assertEquals(START, entity.getStartTime()),
                () -> assertEquals(END, entity.getEndTime()),
                () -> assertEquals(10, entity.getCapacity()),
                () -> assertEquals(3, entity.getReserved())
        );
    }

    @Test
    void shouldAllowNoArg_WhenGymClassEntityDefaultConstructorCalled() {

        assertDoesNotThrow(() -> new GymClassEntity());
    }

    @Test
    void shouldUpdateReserved_WhenSetReservedCalled() {

        GymClassEntity entity = new GymClassEntity(UUID.randomUUID(), "Yoga", "YOGA", START, END, 10, 0);

        entity.setReserved(5);

        assertEquals(5, entity.getReserved());
    }

    // ── ReservationEntity ───────────────────────────────────────────────────────

    @Test
    void shouldStoreAllFields_WhenReservationEntityCreatedWithAllArgs() {

        UUID id      = UUID.randomUUID();
        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        ReservationEntity entity = new ReservationEntity(id, userId, classId, "ACTIVE");

        assertAll(
                () -> assertEquals(id, entity.getId()),
                () -> assertEquals(userId, entity.getUserId()),
                () -> assertEquals(classId, entity.getClassId()),
                () -> assertEquals("ACTIVE", entity.getStatus())
        );
    }

    @Test
    void shouldAllowNoArg_WhenReservationEntityDefaultConstructorCalled() {

        assertDoesNotThrow(() -> new ReservationEntity());
    }

    @Test
    void shouldUpdateStatus_WhenSetStatusCalled() {

        ReservationEntity entity = new ReservationEntity(
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), "ACTIVE");

        entity.setStatus("CANCELLED");

        assertEquals("CANCELLED", entity.getStatus());
    }

    // ── UserEntity ──────────────────────────────────────────────────────────────

    @Test
    void shouldStoreAllFields_WhenUserEntityCreatedWithAllArgs() {

        UUID id = UUID.randomUUID();
        UserEntity entity = new UserEntity(id, "user@test.com", "secure123", "USER", true);

        assertAll(
                () -> assertEquals(id, entity.getId()),
                () -> assertEquals("user@test.com", entity.getEmail()),
                () -> assertEquals("secure123", entity.getPassword()),
                () -> assertEquals("USER", entity.getRole()),
                () -> assertTrue(entity.isActive())
        );
    }

    @Test
    void shouldAllowNoArg_WhenUserEntityDefaultConstructorCalled() {

        assertDoesNotThrow(() -> new UserEntity());
    }

    @Test
    void shouldUpdateAllFields_WhenUserEntitySettersCalled() {

        UUID id = UUID.randomUUID();
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setEmail("new@test.com");
        entity.setPassword("newpass1");
        entity.setRole("ADMIN");
        entity.setActive(false);

        assertAll(
                () -> assertEquals(id, entity.getId()),
                () -> assertEquals("new@test.com", entity.getEmail()),
                () -> assertEquals("newpass1", entity.getPassword()),
                () -> assertEquals("ADMIN", entity.getRole()),
                () -> assertFalse(entity.isActive())
        );
    }
}
