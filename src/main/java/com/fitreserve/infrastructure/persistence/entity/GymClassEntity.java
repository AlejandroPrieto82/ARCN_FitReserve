package com.fitreserve.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "gym_classes")
public class GymClassEntity {

    @Id
    private UUID id;

    private String name;

    private String type;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int capacity;
    private int reserved;

    public GymClassEntity() {}

    public GymClassEntity(UUID id, String name, String type,
                          LocalDateTime startTime, LocalDateTime endTime,
                          int capacity, int reserved) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.reserved = reserved;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public int getCapacity() { return capacity; }
    public int getReserved() { return reserved; }

    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setReserved(int reserved) { this.reserved = reserved; }
}