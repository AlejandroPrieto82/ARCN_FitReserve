package com.fitreserve.domain.valueobject;

import java.time.LocalDateTime;

public class TimeSlot {

    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("TimeSlot cannot be null");
        }

        if (end.isBefore(start)) {
            throw new IllegalArgumentException("End must be after start");
        }

        this.start = start;
        this.end = end;
    }

    public static TimeSlot fromStrings(String start, String end) {
        return new TimeSlot(
                LocalDateTime.parse(start),
                LocalDateTime.parse(end)
        );
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
}