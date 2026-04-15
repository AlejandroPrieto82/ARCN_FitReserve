package com.fitreserve.infrastructure.persistence.mapper;

import java.util.UUID;

public class DomainMapper {

    public static UUID toUUID(String value) {
        return UUID.fromString(value);
    }

    public static String toString(UUID value) {
        return value.toString();
    }
}