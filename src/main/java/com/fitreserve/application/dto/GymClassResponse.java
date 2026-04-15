package com.fitreserve.application.dto;

public class GymClassResponse {

    private String id;
    private String name;
    private String type;
    private String startTime;
    private String endTime;
    private int capacity;
    private int reserved;

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

    public String getId() {
        return id;
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

    public int getReserved() {
        return reserved;
    }
}