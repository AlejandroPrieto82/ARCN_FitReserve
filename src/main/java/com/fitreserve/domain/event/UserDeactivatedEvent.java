package com.fitreserve.domain.event;

public class UserDeactivatedEvent {

    private final String userId;

    public UserDeactivatedEvent(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}