package com.fitreserve.interfaces.rest.request;

public class CreateReservationRequest {

    private final String userId;
    private final String classId;

    public CreateReservationRequest(String userId, String classId) {
        this.userId = userId;
        this.classId = classId;
    }

    public String getUserId() { return userId; }
    public String getClassId() { return classId; }
}