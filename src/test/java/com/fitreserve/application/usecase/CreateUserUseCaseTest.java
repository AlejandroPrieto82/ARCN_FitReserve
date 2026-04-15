package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.interfaces.rest.request.CreateUserRequest;
import com.fitreserve.interfaces.rest.response.UserResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private CreateUserUseCase useCase;

    @Test
    void shouldCreateUserSuccessfully() {

        CreateUserRequest request =
                new CreateUserRequest(
                        "test@mail.com",
                        "123456",
                        "USER"
                );

        when(repository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UserResponse response = useCase.execute(request);

        assertNotNull(response);
        assertEquals("test@mail.com", response.getEmail());
        assertEquals("USER", response.getRole());
        assertTrue(response.isActive());

        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void shouldFailWhenInvalidEmail() {

        CreateUserRequest request =
                new CreateUserRequest(
                        "bad-email",
                        "123456",
                        "USER"
                );

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute(request));

        verify(repository, never()).save(any());
    }
}