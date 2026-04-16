package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeactivateUserUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final DeactivateUserUseCase useCase =
            new DeactivateUserUseCase(userRepository);

    @Test
    void shouldDeactivateUser() {

        String userId = UUID.randomUUID().toString();

        User user = mock(User.class);

        when(userRepository.findById(any()))
                .thenReturn(Optional.of(user));

        useCase.execute(userId);

        verify(user).deactivate();
        verify(userRepository).save(any());
    }

    @Test
    void shouldThrowWhenUserNotFound() {

        String userId = UUID.randomUUID().toString();

        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.execute(userId));
    }
}