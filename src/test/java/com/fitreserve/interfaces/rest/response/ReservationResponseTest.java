package com.fitreserve.interfaces.rest.response;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.valueobject.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationResponseTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    @Test
    void shouldStoreAllFields_WhenCreatedDirectly() {

        ReservationResponse response = new ReservationResponse(
                "res-id", "user-id", "class-id", "Yoga", "start", "end", "ACTIVE");

        assertAll(
                () -> assertEquals("res-id", response.getId()),
                () -> assertEquals("user-id", response.getUserId()),
                () -> assertEquals("class-id", response.getClassId()),
                () -> assertEquals("Yoga", response.getClassName()),
                () -> assertEquals("start", response.getStartTime()),
                () -> assertEquals("end", response.getEndTime()),
                () -> assertEquals("ACTIVE", response.getStatus())
        );
    }

    @Test
    void shouldMapBasicFields_WhenFromReservationOnlyCalled() {

        Reservation reservation = new Reservation(
                ReservationId.generate(), UserId.generate(), ClassId.generate());

        ReservationResponse response = ReservationResponse.from(reservation);

        assertAll(
                () -> assertNotNull(response.getId()),
                () -> assertNotNull(response.getUserId()),
                () -> assertNotNull(response.getClassId()),
                () -> assertEquals("ACTIVE", response.getStatus()),
                () -> assertNull(response.getClassName()),
                () -> assertNull(response.getStartTime()),
                () -> assertNull(response.getEndTime())
        );
    }

    @Test
    void shouldMapAllFields_WhenFromReservationAndGymClassCalled() {

        Reservation reservation = new Reservation(
                ReservationId.generate(), UserId.generate(), ClassId.generate());
        GymClass gymClass = new GymClass(ClassId.generate(), "Yoga", ClassType.YOGA,
                new TimeSlot(START, END), new Cupo(10));

        ReservationResponse response = ReservationResponse.from(reservation, gymClass);

        assertAll(
                () -> assertEquals("Yoga", response.getClassName()),
                () -> assertNotNull(response.getStartTime()),
                () -> assertNotNull(response.getEndTime())
        );
    }

    @Test
    void shouldSetNullFields_WhenFromReservationWithNullGymClassCalled() {

        Reservation reservation = new Reservation(
                ReservationId.generate(), UserId.generate(), ClassId.generate());

        ReservationResponse response = ReservationResponse.from(reservation, null);

        assertAll(
                () -> assertNull(response.getClassName()),
                () -> assertNull(response.getStartTime()),
                () -> assertNull(response.getEndTime())
        );
    }
}
