package com.fitreserve.application.dto;

public class ReservationResponse {

    private String id;
    private String userId;
    private String classId;
    private String status;

    public ReservationResponse(String id, String userId, String classId, String status) {
        this.id = id;
        this.userId = userId;
        this.classId = classId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getClassId() {
        return classId;
    }

    public String getStatus() {
        return status;
    }
}