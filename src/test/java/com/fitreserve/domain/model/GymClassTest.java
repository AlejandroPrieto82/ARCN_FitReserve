package com.fitreserve.domain.model;

import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.Cupo;
import com.fitreserve.domain.valueobject.TimeSlot;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GymClassTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    private GymClass buildClass(int capacity) {
        return new GymClass(ClassId.generate(), "Yoga", ClassType.YOGA,
                new TimeSlot(START, END), new Cupo(capacity));
    }

    @Test
    void shouldInitializeWithZeroReservedAndAvailableSpots_WhenCreated() {

        GymClass gymClass = buildClass(10);

        assertAll(
                () -> assertEquals(0, gymClass.getReserved()),
                () -> assertTrue(gymClass.hasAvailableSpots())
        );
    }

    @Test
    void shouldReturnCorrectAttributes_WhenCreated() {

        ClassId id = ClassId.generate();
        TimeSlot timeSlot = new TimeSlot(START, END);
        Cupo cupo = new Cupo(10);

        GymClass gymClass = new GymClass(id, "Yoga Flow", ClassType.YOGA, timeSlot, cupo);

        assertAll(
                () -> assertSame(id, gymClass.getId()),
                () -> assertEquals("Yoga Flow", gymClass.getName()),
                () -> assertEquals(ClassType.YOGA, gymClass.getType()),
                () -> assertSame(timeSlot, gymClass.getTimeSlot()),
                () -> assertSame(cupo, gymClass.getCapacity())
        );
    }

    @Test
    void shouldHaveAvailableSpots_WhenReservedIsLessThanCapacity() {

        GymClass gymClass = buildClass(5);
        gymClass.reserveSpot();

        assertTrue(gymClass.hasAvailableSpots());
    }

    @Test
    void shouldNotHaveAvailableSpots_WhenReservedEqualsCapacity() {

        GymClass gymClass = buildClass(1);
        gymClass.reserveSpot();

        assertFalse(gymClass.hasAvailableSpots());
    }

    @Test
    void shouldIncrementReserved_WhenReserveSpotCalled() {

        GymClass gymClass = buildClass(5);

        gymClass.reserveSpot();
        gymClass.reserveSpot();

        assertEquals(2, gymClass.getReserved());
    }

    @Test
    void shouldThrowRuntimeException_WhenReserveSpotCalledOnFullClass() {

        GymClass gymClass = buildClass(1);
        gymClass.reserveSpot();

        assertThrows(RuntimeException.class, gymClass::reserveSpot);
    }

    @Test
    void shouldDecrementReserved_WhenUnreserveSpotCalled() {

        GymClass gymClass = buildClass(5);
        gymClass.reserveSpot();
        gymClass.reserveSpot();

        gymClass.unreserveSpot();

        assertEquals(1, gymClass.getReserved());
    }

    @Test
    void shouldNotDecrementBelowZero_WhenUnreserveSpotCalledWithZeroReserved() {

        GymClass gymClass = buildClass(5);

        gymClass.unreserveSpot();

        assertEquals(0, gymClass.getReserved());
    }

    @Test
    void shouldUpdateName_WhenUpdateNameCalled() {

        GymClass gymClass = buildClass(5);

        gymClass.updateName("CrossFit Advanced");

        assertEquals("CrossFit Advanced", gymClass.getName());
    }
}
