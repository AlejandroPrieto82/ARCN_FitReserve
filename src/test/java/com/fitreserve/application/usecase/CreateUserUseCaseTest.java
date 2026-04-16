package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
}