package com.fitreserve.domain.model;

import com.fitreserve.domain.valueobject.*;

public class User {

    private final UserId id;
    private final Email email;
    private final Password password;
    private final UserRole role;
    private boolean active;

    private User(UserId id, Email email, Password password, UserRole role, boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public static User create(Email email, Password password, UserRole role) {
        return new User(
                UserId.generate(),
                email,
                password,
                role,
                true
        );
    }

    // 🔥 RECONSTRUCCIÓN (BD / TESTS / PERSISTENCIA)
    public static User restore(UserId id, Email email, Password password, UserRole role, boolean active) {
        return new User(id, email, password, role, active);
    }

    public UserId getId() { return id; }
    public Email getEmail() { return email; }
    public Password getPassword() { return password; }
    public UserRole getRole() { return role; }
    public boolean isActive() { return active; }

    public void deactivate() {
        this.active = false;
    }
}