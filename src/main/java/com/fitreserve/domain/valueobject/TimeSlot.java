package com.fitreserve.domain.valueobject;

import java.time.LocalDateTime;

public class TimeSlot {

    private final LocalDateTime start;
    private final int durationMinutes;

    public TimeSlot(LocalDateTime start, int durationMinutes) {

        if (start == null) {
            throw new IllegalArgumentException("Start time cannot be null");
        }

        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be > 0");
        }

        this.start = start;
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return start.plusMinutes(durationMinutes);
    }

    public boolean overlaps(TimeSlot other) {
        return !(this.getEnd().isBefore(other.start) ||
                 this.start.isAfter(other.getEnd()));
    }
}