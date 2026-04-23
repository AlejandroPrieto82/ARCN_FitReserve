package com.fitreserve.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void shouldCreatePassword_WhenLengthIsExactlyMinimum() {

        Password password = new Password("123456");

        assertEquals("123456", password.getValue());
    }

    @Test
    void shouldCreatePassword_WhenLengthExceedsMinimum() {

        Password password = new Password("supersecurepassword");

        assertEquals("supersecurepassword", password.getValue());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenPasswordHasFiveCharacters() {

        assertThrows(IllegalArgumentException.class, () -> new Password("12345"));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenPasswordIsEmpty() {

        assertThrows(IllegalArgumentException.class, () -> new Password(""));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenPasswordIsNull() {

        assertThrows(IllegalArgumentException.class, () -> new Password(null));
    }
}
