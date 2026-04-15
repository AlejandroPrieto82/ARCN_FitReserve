package com.fitreserve.domain.model;

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

    public boolean hasAvailableSpots() {
        return reserved < capacity.getValue();
    }

    public void reserveSpot() {
        if (!hasAvailableSpots()) {
            throw new RuntimeException("No spots available");
        }
        reserved++;
    }

    public ClassId getId() { return id; }
    public String getName() { return name; }
    public ClassType getType() { return type; }
    public Cupo getCapacity() { return capacity; }
}