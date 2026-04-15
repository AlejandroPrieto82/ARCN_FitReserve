package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.*;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getRole().name(),
                user.isActive()
        );
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                new UserId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                UserRole.valueOf(entity.getRole()),
                entity.isActive()
        );
    }
}