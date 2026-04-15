package com.fitreserve.domain.model;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.valueobject.*;

public class User {

    private final UserId id;
    private final Email email;
    private Password password;
    private final Role role;
    private boolean active;

    public User(UserId id, Email email, Password password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
    }

    public void deactivate() {
        if (!active) {
            throw new BusinessException("User already deactivated");
        }
        this.active = false;
    }

    public boolean isActive() {
        return active;
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

    public Role getRole() {
        return role;
    }
}