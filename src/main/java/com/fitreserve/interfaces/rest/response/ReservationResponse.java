package com.fitreserve.interfaces.rest.response;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.Reservation;

public class ReservationResponse {

    private final String id;
    private final String userId;
    private final String classId;
    private final String className;
    private final String startTime;
    private final String endTime;
    private final String status;

    public ReservationResponse(String id, String userId, String classId,
                               String className, String startTime, String endTime,
                               String status) {
        this.id = id;
        this.userId = userId;
        this.classId = classId;
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getClassId() { return classId; }
    public String getClassName() { return className; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getStatus() { return status; }

    public static ReservationResponse from(Reservation r) {
        return new ReservationResponse(
                r.getId().getValue().toString(),
                r.getUserId().getValue().toString(),
                r.getClassId().getValue().toString(),
                null,
                null,
                null,
                r.getStatus().name()
        );
    }

    public static ReservationResponse from(Reservation r, GymClass gymClass) {
        return new ReservationResponse(
                r.getId().getValue().toString(),
                r.getUserId().getValue().toString(),
                r.getClassId().getValue().toString(),
                gymClass != null ? gymClass.getName() : null,
                gymClass != null ? gymClass.getTimeSlot().getStart().toString() : null,
                gymClass != null ? gymClass.getTimeSlot().getEnd().toString() : null,
                r.getStatus().name()
        );
    }
}