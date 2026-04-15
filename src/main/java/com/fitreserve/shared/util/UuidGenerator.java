package com.fitreserve.shared.util;

import java.util.UUID;

public class UuidGenerator {

    public static UUID generate() {
        return UUID.randomUUID();
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}