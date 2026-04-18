package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.Password;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginUseCaseTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final LoginUseCase useCase = new LoginUseCase(userRepository);

    @Test
    void shouldLoginSuccessfully() {

        String email = "test@mail.com";
        String password = "ValidPass123";
        User mockUser = mock(User.class);
        Password mockPassword = mock(Password.class);

        when(mockPassword.getValue()).thenReturn(password);
        when(mockUser.isActive()).thenReturn(true);
        when(mockUser.getPassword()).thenReturn(mockPassword);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        User result = useCase.execute(email, password);

        assertSame(mockUser, result);
        verify(userRepository).findByEmail(email);
    }

    @Test
    void shouldThrowWhenUserNotFound() {

        String email = "notfound@mail.com";
        String password = "ValidPass123";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.execute(email, password));
    }

    @Test
    void shouldThrowWhenUserInactive() {

        String email = "test@mail.com";
        String password = "ValidPass123";
        User mockUser = mock(User.class);

        when(mockUser.isActive()).thenReturn(false);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        assertThrows(RuntimeException.class,
                () -> useCase.execute(email, password));
    }

    @Test
    void shouldThrowWhenPasswordInvalid() {

        String email = "test@mail.com";
        String correctPassword = "ValidPass123";
        String wrongPassword = "WrongPass456";
        User mockUser = mock(User.class);
        Password mockPassword = mock(Password.class);

        when(mockPassword.getValue()).thenReturn(correctPassword);
        when(mockUser.isActive()).thenReturn(true);
        when(mockUser.getPassword()).thenReturn(mockPassword);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));

        assertThrows(RuntimeException.class,
                () -> useCase.execute(email, wrongPassword));
    }
}
