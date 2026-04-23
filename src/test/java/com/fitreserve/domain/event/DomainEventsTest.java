package com.fitreserve.domain.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DomainEventsTest {

    @Test
    void shouldStoreClassIdAndName_WhenClassCreatedEventCreated() {

        ClassCreatedEvent event = new ClassCreatedEvent("class-1", "Yoga");

        assertAll(
                () -> assertEquals("class-1", event.getClassId()),
                () -> assertEquals("Yoga", event.getName())
        );
    }

    @Test
    void shouldStoreClassId_WhenClassFullEventCreated() {

        ClassFullEvent event = new ClassFullEvent("class-1");

        assertEquals("class-1", event.getClassId());
    }

    @Test
    void shouldStoreAllIds_WhenReservationCreatedEventCreated() {

        ReservationCreatedEvent event = new ReservationCreatedEvent("res-1", "user-1", "class-1");

        assertAll(
                () -> assertEquals("res-1", event.getReservationId()),
                () -> assertEquals("user-1", event.getUserId()),
                () -> assertEquals("class-1", event.getClassId())
        );
    }

    @Test
    void shouldStoreReservationId_WhenReservationCancelledEventCreated() {

        ReservationCancelledEvent event = new ReservationCancelledEvent("res-1");

        assertEquals("res-1", event.getReservationId());
    }

    @Test
    void shouldStoreUserId_WhenUserDeactivatedEventCreated() {

        UserDeactivatedEvent event = new UserDeactivatedEvent("user-1");

        assertEquals("user-1", event.getUserId());
    }
}
