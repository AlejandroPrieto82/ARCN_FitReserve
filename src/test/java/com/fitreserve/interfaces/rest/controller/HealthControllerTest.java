package com.fitreserve.interfaces.rest.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    @Test
    void shouldReturnStatusUp_WhenHealthCalled() {

        HealthController controller = new HealthController();

        Map<String, String> result = controller.health();

        assertEquals("UP", result.get("status"));
    }
}
