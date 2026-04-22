package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fitreserve.domain.model.UserRole;

class CreateUserUseCaseTest {

    private final UserRepository repository = mock(UserRepository.class);
    private final CreateUserUseCase useCase = new CreateUserUseCase(repository);

    @Test
    void shouldCreateUserSuccessfully() {

        User user = mock(User.class);

        when(repository.save(any(User.class))).thenReturn(user);

        User result = useCase.execute(
                "test@mail.com",
                "StrongPass123", // 🔥 FIX: password válida
                "ADMIN"
        );

        assertNotNull(result);
        verify(repository).save(any(User.class));
    }

    @Test
    void shouldFailWhenInvalidRole() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute(
                        "test@mail.com",
                        "StrongPass123",
                        "INVALID_ROLE"
                ));
    }

    @Test
    void shouldCreateUser_WhenRoleIsLowercase() {

        when(repository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        User result = useCase.execute("user@test.com", "secure123", "user");

        assertEquals(UserRole.USER, result.getRole());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEmailIsInvalid() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("not-an-email", "StrongPass123", "USER"));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEmailIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute(null, "StrongPass123", "USER"));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenPasswordIsTooShort() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("user@test.com", "12345", "USER"));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenPasswordIsNull() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("user@test.com", null, "USER"));
    }
}