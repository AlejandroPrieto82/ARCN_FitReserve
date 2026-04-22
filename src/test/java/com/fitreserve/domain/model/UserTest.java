package com.fitreserve.domain.model;

import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Password;
import com.fitreserve.domain.valueobject.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateActiveUserWithGeneratedId_WhenCreateCalled() {

        User user = User.create(new Email("user@test.com"), new Password("secure123"), UserRole.USER);

        assertAll(
                () -> assertNotNull(user.getId()),
                () -> assertEquals("user@test.com", user.getEmail().getValue()),
                () -> assertEquals("secure123", user.getPassword().getValue()),
                () -> assertEquals(UserRole.USER, user.getRole()),
                () -> assertTrue(user.isActive())
        );
    }

    @Test
    void shouldGenerateUniqueIds_WhenMultipleUsersCreated() {

        User user1 = User.create(new Email("one@test.com"), new Password("pass123"), UserRole.USER);
        User user2 = User.create(new Email("two@test.com"), new Password("pass123"), UserRole.USER);

        assertNotEquals(user1.getId().getValue(), user2.getId().getValue());
    }

    @Test
    void shouldDeactivateUser_WhenDeactivateCalled() {

        User user = User.create(new Email("user@test.com"), new Password("secure123"), UserRole.USER);

        user.deactivate();

        assertFalse(user.isActive());
    }

    @Test
    void shouldRestoreActiveUser_WhenRestoreCalledWithActiveTrue() {

        UserId id = UserId.generate();

        User user = User.restore(id, new Email("admin@test.com"),
                new Password("secure123"), UserRole.ADMIN, true);

        assertAll(
                () -> assertEquals(id.getValue(), user.getId().getValue()),
                () -> assertEquals(UserRole.ADMIN, user.getRole()),
                () -> assertTrue(user.isActive())
        );
    }

    @Test
    void shouldRestoreInactiveUser_WhenRestoreCalledWithActiveFalse() {

        User user = User.restore(UserId.generate(), new Email("inactive@test.com"),
                new Password("secure123"), UserRole.USER, false);

        assertFalse(user.isActive());
    }
}
