package com.fitreserve.domain.model;

import com.fitreserve.domain.valueobject.*;

public class User {

    private final UserId id;
    private final Email email;
    private final Password password;
    private final UserRole role;
    private boolean active;

    public User(UserId id, Email email, Password password, UserRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public UserId getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}