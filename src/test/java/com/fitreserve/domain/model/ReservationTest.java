package com.fitreserve.domain.model;

import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.ReservationId;
import com.fitreserve.domain.valueobject.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void shouldHaveActiveStatusAndCorrectIds_WhenCreated() {

        ReservationId reservationId = ReservationId.generate();
        UserId userId = UserId.generate();
        ClassId classId = ClassId.generate();

        Reservation reservation = new Reservation(reservationId, userId, classId);

        assertAll(
                () -> assertEquals(reservationId.getValue(), reservation.getId().getValue()),
                () -> assertEquals(userId.getValue(), reservation.getUserId().getValue()),
                () -> assertEquals(classId.getValue(), reservation.getClassId().getValue()),
                () -> assertEquals(ReservationStatus.ACTIVE, reservation.getStatus())
        );
    }

    @Test
    void shouldTransitionToCancelled_WhenCancelCalled() {

        Reservation reservation = new Reservation(
                ReservationId.generate(), UserId.generate(), ClassId.generate());

        reservation.cancel();

        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void shouldRemainsActive_WhenCancelNotCalled() {

        Reservation reservation = new Reservation(
                ReservationId.generate(), UserId.generate(), ClassId.generate());

        assertEquals(ReservationStatus.ACTIVE, reservation.getStatus());
    }
}
