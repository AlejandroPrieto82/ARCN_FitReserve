package com.fitreserve.interfaces.rest.request;

public class CreateGymClassRequest {

    private final String name;
    private final String type;
    private final String startTime;
    private final String endTime;
    private final int capacity;

    public CreateGymClassRequest(String name, String type,
            String startTime, String endTime,
            int capacity) {
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getCapacity() {
        return capacity;
    }
}