package com.fitreserve.domain.event;

public class ClassCreatedEvent {

    private final String classId;
    private final String name;

    public ClassCreatedEvent(String classId, String name) {
        this.classId = classId;
        this.name = name;
    }

    public String getClassId() {
        return classId;
    }

    public String getName() {
        return name;
    }
}