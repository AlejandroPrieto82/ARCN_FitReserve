package com.fitreserve.shared.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void shouldParseISOString_WhenValidDateTimeStringProvided() {

        LocalDateTime result = DateUtils.parse("2026-04-21T10:00:00");

        assertEquals(LocalDateTime.of(2026, 4, 21, 10, 0, 0), result);
    }

    @Test
    void shouldFormatLocalDateTime_WhenLocalDateTimeProvided() {

        LocalDateTime dateTime = LocalDateTime.of(2026, 4, 21, 10, 0, 0);

        String result = DateUtils.format(dateTime);

        assertEquals("2026-04-21T10:00:00", result);
    }

    @Test
    void shouldRoundTrip_WhenParsingAndFormatting() {

        String original = "2026-12-31T23:59:59";

        String result = DateUtils.format(DateUtils.parse(original));

        assertEquals(original, result);
    }

    @Test
    void shouldThrowException_WhenStringIsInvalidFormat() {

        assertThrows(Exception.class, () -> DateUtils.parse("not-a-date"));
    }

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        DateUtils instance = new DateUtils();

        assertNotNull(instance);
    }
}
