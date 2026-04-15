package com.fitreserve.domain.model;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.valueobject.*;

public class Reservation {

    private final ReservationId id;
    private final UserId userId;
    private final ClassId classId;
    private ReservationStatus status;

    public Reservation(ReservationId id, UserId userId, ClassId classId) {
        this.id = id;
        this.userId = userId;
        this.classId = classId;
        this.status = ReservationStatus.ACTIVE;
    }

    public void cancel() {
        if (status == ReservationStatus.CANCELLED) {
            throw new BusinessException("Reservation already cancelled");
        }
        this.status = ReservationStatus.CANCELLED;
    }

    public boolean isActive() {
        return status == ReservationStatus.ACTIVE;
    }

    public ReservationId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public ClassId getClassId() {
        return classId;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}