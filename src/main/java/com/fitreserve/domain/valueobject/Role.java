package com.fitreserve.domain.valueobject;

public enum Role {
    USER,
    ADMIN,
    TRAINER;

    public boolean isAdmin() {
        return this == ADMIN;
    }
}