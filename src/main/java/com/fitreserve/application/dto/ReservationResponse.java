package com.fitreserve.application.dto;

public class ReservationResponse {

    private final String id;
    private final String userId;
    private final String classId;
    private final String status;

    public ReservationResponse(String id, String userId, String classId, String status) {
        this.id = id;
        this.userId = userId;
        this.classId = classId;
        this.status = status;
    }

    public String getId() { return id; }
}