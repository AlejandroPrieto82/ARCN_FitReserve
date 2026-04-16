package com.fitreserve.interfaces.rest.response;

import com.fitreserve.domain.model.GymClass;

public class GymClassResponse {

    private final String id;
    private final String name;
    private final String type;
    private final String startTime;
    private final String endTime;
    private final int capacity;
    private final int reserved;

    public GymClassResponse(String id, String name, String type,
                            String startTime, String endTime,
                            int capacity, int reserved) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.reserved = reserved;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public int getCapacity() { return capacity; }
    public int getReserved() { return reserved; }

    public static GymClassResponse from(GymClass gymClass) {
        return new GymClassResponse(
                gymClass.getId().getValue().toString(),
                gymClass.getName(),
                gymClass.getType().name(),
                gymClass.getTimeSlot().getStart().toString(),
                gymClass.getTimeSlot().getEnd().toString(),
                gymClass.getCapacity().getValue(),
                gymClass.getReserved()
        );
    }
}