package com.fitreserve.interfaces.rest.response;

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
    public String getUserId() { return userId; }
    public String getClassId() { return classId; }
    public String getStatus() { return status; }
}