package com.fitreserve.shared.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiResponseTest {

    @Test
    void shouldStoreAndReturnData_WhenConstructedDirectly() {

        ApiResponse<String> response = new ApiResponse<>("hello");

        assertEquals("hello", response.getData());
    }

    @Test
    void shouldStoreAndReturnData_WhenCreatedViaOf() {

        ApiResponse<Integer> response = ApiResponse.of(42);

        assertEquals(42, response.getData());
    }

    @Test
    void shouldAcceptNull_WhenDataIsNull() {

        ApiResponse<String> response = ApiResponse.of(null);

        assertNull(response.getData());
    }

    @Test
    void shouldWorkWithComplexTypes_WhenDataIsObject() {

        ApiResponse<String[]> response = ApiResponse.of(new String[]{"a", "b"});

        assertNotNull(response.getData());
    }
}
