package com.fitreserve.domain.valueobject;

public class Password {

    private final String value;

    public Password(String value) {
        if (value == null || value.length() < 6) {
            throw new IllegalArgumentException("Password too short");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}