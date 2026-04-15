package com.fitreserve.interfaces.rest.request;

public class CreateUserRequest {
    private String email;
    private String password;
    private String role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}