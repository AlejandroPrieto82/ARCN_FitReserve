package com.fitreserve.interfaces.rest.response;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.Cupo;
import com.fitreserve.domain.valueobject.TimeSlot;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GymClassResponseTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    @Test
    void shouldStoreAllFields_WhenCreatedDirectly() {

        GymClassResponse response = new GymClassResponse(
                "id", "Yoga", "YOGA", "2026-04-21T10:00", "2026-04-21T11:00", 10, 3);

        assertAll(
                () -> assertEquals("id", response.getId()),
                () -> assertEquals("Yoga", response.getName()),
                () -> assertEquals("YOGA", response.getType()),
                () -> assertEquals("2026-04-21T10:00", response.getStartTime()),
                () -> assertEquals("2026-04-21T11:00", response.getEndTime()),
                () -> assertEquals(10, response.getCapacity()),
                () -> assertEquals(3, response.getReserved())
        );
    }

    @Test
    void shouldMapAllFields_WhenFromGymClassCalled() {

        ClassId classId = ClassId.generate();
        GymClass gymClass = new GymClass(classId, "Yoga Flow", ClassType.YOGA,
                new TimeSlot(START, END), new Cupo(10));

        GymClassResponse response = GymClassResponse.from(gymClass);

        assertAll(
                () -> assertEquals(classId.getValue().toString(), response.getId()),
                () -> assertEquals("Yoga Flow", response.getName()),
                () -> assertEquals("YOGA", response.getType()),
                () -> assertEquals(10, response.getCapacity()),
                () -> assertEquals(0, response.getReserved()),
                () -> assertNotNull(response.getStartTime()),
                () -> assertNotNull(response.getEndTime())
        );
    }

    @Test
    void shouldReflectReservedCount_WhenClassHasReservedSpots() {

        GymClass gymClass = new GymClass(ClassId.generate(), "CrossFit", ClassType.CROSSFIT,
                new TimeSlot(START, END), new Cupo(10));
        gymClass.reserveSpot();
        gymClass.reserveSpot();

        GymClassResponse response = GymClassResponse.from(gymClass);

        assertEquals(2, response.getReserved());
    }
}
