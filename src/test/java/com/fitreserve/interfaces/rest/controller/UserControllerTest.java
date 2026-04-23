package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateUserUseCase;
import com.fitreserve.application.usecase.DeactivateUserUseCase;
import com.fitreserve.application.usecase.LoginUseCase;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.model.UserRole;
import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Password;
import com.fitreserve.interfaces.rest.request.CreateUserRequest;
import com.fitreserve.interfaces.rest.request.LoginRequest;
import com.fitreserve.interfaces.rest.response.UserResponse;
import com.fitreserve.shared.util.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private CreateUserUseCase createUserUseCase;
    @Mock
    private DeactivateUserUseCase deactivateUserUseCase;
    @Mock
    private LoginUseCase loginUseCase;

    @InjectMocks
    private UserController controller;

    @Test
    void shouldReturnCreatedUser_WhenCreateCalled() {

        User user = User.create(new Email("user@test.com"), new Password("secure123"), UserRole.USER);
        when(createUserUseCase.execute("user@test.com", "secure123", "USER")).thenReturn(user);
        CreateUserRequest request = new CreateUserRequest("user@test.com", "secure123", "USER");

        ApiResponse<UserResponse> result = controller.create(request);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("user@test.com", result.getData().getEmail()),
                () -> assertEquals("USER", result.getData().getRole())
        );
    }

    @Test
    void shouldReturnUser_WhenLoginCalled() {

        User user = User.create(new Email("user@test.com"), new Password("secure123"), UserRole.USER);
        when(loginUseCase.execute("user@test.com", "secure123")).thenReturn(user);
        LoginRequest request = new LoginRequest("user@test.com", "secure123");

        ApiResponse<UserResponse> result = controller.login(request);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("user@test.com", result.getData().getEmail()),
                () -> assertTrue(result.getData().isActive())
        );
    }

    @Test
    void shouldReturnDeactivatedMessage_WhenDeactivateCalled() {

        String userId = UUID.randomUUID().toString();
        doNothing().when(deactivateUserUseCase).execute(userId);

        ApiResponse<String> result = controller.deactivate(userId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("User deactivated", result.getData())
        );
        verify(deactivateUserUseCase).execute(userId);
    }
}
