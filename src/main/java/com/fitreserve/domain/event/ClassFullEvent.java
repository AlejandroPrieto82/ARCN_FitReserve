package com.fitreserve.domain.event;

public class ClassFullEvent {

    private final String classId;

    public ClassFullEvent(String classId) {
        this.classId = classId;
    }

    public String getClassId() {
        return classId;
    }
}