package com.fitreserve.interfaces.rest.response;

import com.fitreserve.domain.model.Reservation;

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

    public static ReservationResponse from(Reservation r) {
        return new ReservationResponse(
                r.getId().getValue().toString(),
                r.getUserId().getValue().toString(),
                r.getClassId().getValue().toString(),
                r.getStatus().name()
        );
    }
}