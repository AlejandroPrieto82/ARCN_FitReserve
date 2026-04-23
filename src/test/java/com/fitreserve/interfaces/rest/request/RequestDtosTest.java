package com.fitreserve.interfaces.rest.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestDtosTest {

    @Test
    void shouldStoreAllFields_WhenCreateGymClassRequestCreated() {

        CreateGymClassRequest req = new CreateGymClassRequest(
                "Yoga", "YOGA", "2026-04-21T10:00:00", "2026-04-21T11:00:00", 10);

        assertAll(
                () -> assertEquals("Yoga", req.getName()),
                () -> assertEquals("YOGA", req.getType()),
                () -> assertEquals("2026-04-21T10:00:00", req.getStartTime()),
                () -> assertEquals("2026-04-21T11:00:00", req.getEndTime()),
                () -> assertEquals(10, req.getCapacity())
        );
    }

    @Test
    void shouldStoreAllFields_WhenCreateReservationRequestCreated() {

        CreateReservationRequest req = new CreateReservationRequest("user-id", "class-id");

        assertAll(
                () -> assertEquals("user-id", req.getUserId()),
                () -> assertEquals("class-id", req.getClassId())
        );
    }

    @Test
    void shouldStoreAllFields_WhenCreateUserRequestCreated() {

        CreateUserRequest req = new CreateUserRequest("user@test.com", "password123", "USER");

        assertAll(
                () -> assertEquals("user@test.com", req.getEmail()),
                () -> assertEquals("password123", req.getPassword()),
                () -> assertEquals("USER", req.getRole())
        );
    }

    @Test
    void shouldStoreAllFields_WhenLoginRequestCreated() {

        LoginRequest req = new LoginRequest("user@test.com", "password123");

        assertAll(
                () -> assertEquals("user@test.com", req.getEmail()),
                () -> assertEquals("password123", req.getPassword())
        );
    }
}
