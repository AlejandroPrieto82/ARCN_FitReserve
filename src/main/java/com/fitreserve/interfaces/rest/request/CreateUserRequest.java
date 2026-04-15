package com.fitreserve.interfaces.rest.request;

public class CreateUserRequest {

    private final String email;
    private final String password;
    private final String role;

    public CreateUserRequest(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
}