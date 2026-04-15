package com.fitreserve.application.dto;

public class UserResponse {

    private String id;
    private String email;
    private String role;
    private boolean active;

    public UserResponse(String id, String email, String role, boolean active) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}