package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.infrastructure.persistence.entity.ClassTypeEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClassTypeMapper {

    public ClassType toDomain(ClassTypeEntity entity) {
        return ClassType.valueOf(entity.getName());
    }

    public ClassTypeEntity toEntity(ClassType type) {
        return new ClassTypeEntity(
                UUID.randomUUID(),
                type.name()
        );
    }
}