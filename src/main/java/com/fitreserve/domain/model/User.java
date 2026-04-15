package com.fitreserve.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Role;

public class User {

    private UUID id;
    private String name;
    private Email email;
    private String passwordHash;
    private boolean active;
    private Role role;
    private LocalDateTime createdAt;

    public User(UUID id, String name, Email email, String passwordHash, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    // 🔒 Business rules
    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public void changeRole(Role newRole) {
        this.role = newRole;
    }

    public boolean isActive() {
        return active;
    }

    public UUID getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}