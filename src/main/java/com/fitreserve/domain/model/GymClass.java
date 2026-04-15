package com.fitreserve.domain.model;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.valueobject.*;

public class GymClass {

    private final ClassId id;
    private final String name;
    private final ClassType type;
    private final TimeSlot timeSlot;
    private final Cupo capacity;
    private int reserved;

    public GymClass(ClassId id, String name, ClassType type, TimeSlot timeSlot, Cupo capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.timeSlot = timeSlot;
        this.capacity = capacity;
        this.reserved = 0;
    }

    public void reserveSpot() {
        if (reserved >= capacity.getValue()) {
            throw new BusinessException("Class is full");
        }
        reserved++;
    }

    public void cancelReservation() {
        if (reserved <= 0) {
            throw new BusinessException("No reservations to cancel");
        }
        reserved--;
    }

    public boolean isFull() {
        return reserved >= capacity.getValue();
    }

    public ClassId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ClassType getType() {
        return type;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public int getReserved() {
        return reserved;
    }

    public Cupo getCapacity() {
        return capacity;
    }
}