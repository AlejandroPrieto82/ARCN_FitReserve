package com.fitreserve.interfaces.rest.request;

public class CreateGymClassRequest {

    private String name;
    private String type;
    private String startTime;
    private String endTime;
    private int capacity;

    public String getName() { return name; }
    public String getType() { return type; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public int getCapacity() { return capacity; }
}