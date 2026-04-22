package com.fitreserve.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CupoTest {

    @Test
    void shouldCreateCupo_WhenValueIsOne() {

        Cupo cupo = new Cupo(1);

        assertEquals(1, cupo.getValue());
    }

    @Test
    void shouldCreateCupo_WhenValueIsLarge() {

        Cupo cupo = new Cupo(100);

        assertEquals(100, cupo.getValue());
    }

    @Test
    void shouldReturnTrue_WhenCurrentReservationsEqualCapacity() {

        Cupo cupo = new Cupo(5);

        assertTrue(cupo.isFull(5));
    }

    @Test
    void shouldReturnTrue_WhenCurrentReservationsExceedCapacity() {

        Cupo cupo = new Cupo(5);

        assertTrue(cupo.isFull(6));
    }

    @Test
    void shouldReturnFalse_WhenCurrentReservationsBelowCapacity() {

        Cupo cupo = new Cupo(5);

        assertFalse(cupo.isFull(4));
    }

    @Test
    void shouldReturnFalse_WhenCurrentReservationsIsZero() {

        Cupo cupo = new Cupo(5);

        assertFalse(cupo.isFull(0));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsZero() {

        assertThrows(IllegalArgumentException.class, () -> new Cupo(0));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenValueIsNegative() {

        assertThrows(IllegalArgumentException.class, () -> new Cupo(-1));
    }
}
