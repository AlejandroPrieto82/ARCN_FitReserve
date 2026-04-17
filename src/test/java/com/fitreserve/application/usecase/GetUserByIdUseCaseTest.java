package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetUserByIdUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final GetUserByIdUseCase useCase = new GetUserByIdUseCase(userRepository);

    @Test
    void shouldReturnUserWhenFound() {

        String userId = UUID.randomUUID().toString();
        User expectedUser = mock(User.class);

        when(userRepository.findById(any()))
                .thenReturn(Optional.of(expectedUser));

        User result = useCase.execute(userId);

        assertSame(expectedUser, result);
        verify(userRepository).findById(any());
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