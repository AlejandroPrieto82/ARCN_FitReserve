package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.model.UserRole;
import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Password;
import com.fitreserve.infrastructure.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void shouldConvertUserToEntity_WhenToEntityCalled() {

        User user = User.create(new Email("user@test.com"), new Password("secure123"), UserRole.USER);

        UserEntity entity = UserMapper.toEntity(user);

        assertAll(
                () -> assertNotNull(entity.getId()),
                () -> assertEquals("user@test.com", entity.getEmail()),
                () -> assertEquals("secure123", entity.getPassword()),
                () -> assertEquals("USER", entity.getRole()),
                () -> assertTrue(entity.isActive())
        );
    }

    @Test
    void shouldConvertEntityToActiveUser_WhenToDomainCalledWithActiveUser() {

        UUID id = UUID.randomUUID();
        UserEntity entity = new UserEntity(id, "admin@test.com", "pass1234", "ADMIN", true);

        User user = UserMapper.toDomain(entity);

        assertAll(
                () -> assertEquals(id, user.getId().getValue()),
                () -> assertEquals("admin@test.com", user.getEmail().getValue()),
                () -> assertEquals("ADMIN", user.getRole().name()),
                () -> assertTrue(user.isActive())
        );
    }

    @Test
    void shouldConvertEntityToInactiveUser_WhenToDomainCalledWithInactiveUser() {

        UUID id = UUID.randomUUID();
        UserEntity entity = new UserEntity(id, "user@test.com", "pass1234", "USER", false);

        User user = UserMapper.toDomain(entity);

        assertFalse(user.isActive());
    }

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        assertDoesNotThrow(() -> new UserMapper());
    }
}
