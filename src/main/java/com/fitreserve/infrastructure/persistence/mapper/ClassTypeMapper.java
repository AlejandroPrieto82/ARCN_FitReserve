package com.fitreserve.infrastructure.persistence.mapper;

import com.fitreserve.domain.model.ClassType;

public class ClassTypeMapper {
    public static ClassType toDomain(String name) {
        return ClassType.valueOf(name);
    }

    public static String toEntity(ClassType type) {
        return type.name();
    }
}