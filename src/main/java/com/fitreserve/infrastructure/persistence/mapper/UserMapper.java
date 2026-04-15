package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        User user = new User(
                new UserId(entity.getId()),
                new Email(entity.getEmail()),
                new Password(entity.getPassword()),
                new Role(entity.getRole())
        );

        if (!entity.isActive()) {
            user.deactivate();
        }

        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();

        entity.setId(user.getId().getValue());
        entity.setEmail(user.getEmail().getValue());
        entity.setPassword(user.getPassword().getValue());
        entity.setRole(user.getRole().getValue());
        entity.setActive(user.isActive());

        return entity;
    }
}