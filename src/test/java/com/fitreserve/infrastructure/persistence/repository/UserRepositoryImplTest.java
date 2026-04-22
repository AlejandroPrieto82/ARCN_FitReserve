package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.model.UserRole;
import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Password;
import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.infrastructure.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    private UserJpaRepository jpaRepository;

    @InjectMocks
    private UserRepositoryImpl repository;

    @Test
    void shouldReturnSavedUser_WhenSaveCalled() {

        UUID id = UUID.randomUUID();
        UserEntity savedEntity = new UserEntity(id, "user@test.com", "secure123", "USER", true);
        when(jpaRepository.save(any())).thenReturn(savedEntity);
        User user = User.create(new Email("user@test.com"), new Password("secure123"), UserRole.USER);

        User result = repository.save(user);

        assertAll(
                () -> assertEquals(id, result.getId().getValue()),
                () -> assertEquals("user@test.com", result.getEmail().getValue())
        );
    }

    @Test
    void shouldReturnUser_WhenFindByIdCalledWithExistingId() {

        UUID id = UUID.randomUUID();
        UserId userId = UserId.from(id);
        UserEntity entity = new UserEntity(id, "user@test.com", "secure123", "USER", true);
        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<User> result = repository.findById(userId);

        assertTrue(result.isPresent());
        assertEquals("user@test.com", result.get().getEmail().getValue());
    }

    @Test
    void shouldReturnEmpty_WhenFindByIdCalledWithNonExistentId() {

        UUID id = UUID.randomUUID();
        UserId userId = UserId.from(id);
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = repository.findById(userId);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnUser_WhenFindByEmailCalledWithExistingEmail() {

        UUID id = UUID.randomUUID();
        UserEntity entity = new UserEntity(id, "user@test.com", "secure123", "USER", true);
        when(jpaRepository.findByEmail("user@test.com")).thenReturn(Optional.of(entity));

        Optional<User> result = repository.findByEmail("user@test.com");

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId().getValue());
    }

    @Test
    void shouldReturnEmpty_WhenFindByEmailCalledWithNonExistentEmail() {

        when(jpaRepository.findByEmail("nobody@test.com")).thenReturn(Optional.empty());

        Optional<User> result = repository.findByEmail("nobody@test.com");

        assertTrue(result.isEmpty());
    }
}
