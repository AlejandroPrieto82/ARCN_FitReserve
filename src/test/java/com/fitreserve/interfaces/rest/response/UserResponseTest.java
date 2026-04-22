package com.fitreserve.interfaces.rest.response;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.model.UserRole;
import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Password;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseTest {

    @Test
    void shouldStoreAllFields_WhenCreatedDirectly() {

        UserResponse response = new UserResponse("user-id", "user@test.com", "USER", true);

        assertAll(
                () -> assertEquals("user-id", response.getId()),
                () -> assertEquals("user@test.com", response.getEmail()),
                () -> assertEquals("USER", response.getRole()),
                () -> assertTrue(response.isActive())
        );
    }

    @Test
    void shouldMapAllFields_WhenFromActiveUserCalled() {

        User user = User.create(new Email("user@test.com"), new Password("secure123"), UserRole.USER);

        UserResponse response = UserResponse.from(user);

        assertAll(
                () -> assertNotNull(response.getId()),
                () -> assertEquals("user@test.com", response.getEmail()),
                () -> assertEquals("USER", response.getRole()),
                () -> assertTrue(response.isActive())
        );
    }

    @Test
    void shouldMapActiveAsFalse_WhenFromInactiveUserCalled() {

        User user = User.create(new Email("admin@test.com"), new Password("secure123"), UserRole.ADMIN);
        user.deactivate();

        UserResponse response = UserResponse.from(user);

        assertAll(
                () -> assertFalse(response.isActive()),
                () -> assertEquals("ADMIN", response.getRole())
        );
    }
}
