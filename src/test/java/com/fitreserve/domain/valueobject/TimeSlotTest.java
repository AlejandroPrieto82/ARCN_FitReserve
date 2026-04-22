package com.fitreserve.domain.valueobject;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    @Test
    void shouldCreateTimeSlot_WhenEndIsAfterStart() {

        TimeSlot slot = new TimeSlot(START, END);

        assertAll(
                () -> assertEquals(START, slot.getStart()),
                () -> assertEquals(END, slot.getEnd())
        );
    }

    @Test
    void shouldCreateFromStrings_WhenValidISOStrings() {

        TimeSlot slot = TimeSlot.fromStrings("2026-04-21T10:00:00", "2026-04-21T11:00:00");

        assertAll(
                () -> assertEquals(START, slot.getStart()),
                () -> assertEquals(END, slot.getEnd())
        );
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenStartIsNull() {

        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(null, END));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEndIsNull() {

        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(START, null));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEndIsBeforeStart() {

        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(END, START));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenStartEqualsEnd() {

        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(START, START));
    }
}
