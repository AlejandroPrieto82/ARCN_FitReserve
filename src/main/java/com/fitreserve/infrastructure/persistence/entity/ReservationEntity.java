package com.fitreserve.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    private UUID id;

    private UUID userId;
    private UUID classId;

    private String status;

    public ReservationEntity() {
    }

    public ReservationEntity(UUID id, UUID userId, UUID classId, String status) {
        this.id = id;
        this.userId = userId;
        this.classId = classId;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getClassId() {
        return classId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}