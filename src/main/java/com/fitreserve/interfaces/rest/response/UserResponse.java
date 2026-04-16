package com.fitreserve.interfaces.rest.response;

import com.fitreserve.domain.model.User;

public class UserResponse {

    private final String id;
    private final String email;
    private final String role;
    private final boolean active;

    public UserResponse(String id, String email, String role, boolean active) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public boolean isActive() { return active; }

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId().getValue().toString(),
                user.getEmail().getValue(),
                user.getRole().name(),
                user.isActive()
        );
    }
}