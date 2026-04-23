package com.fitreserve.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.junit.jupiter.api.Assertions.*;

class InfrastructureConfigTest {

    @Test
    void shouldBeInstantiable_WhenDatabaseConfigCreated() {

        assertDoesNotThrow(() -> new DatabaseConfig());
    }

    @Test
    void shouldBeInstantiable_WhenMapperConfigCreated() {

        assertDoesNotThrow(() -> new MapperConfig());
    }

    @Test
    void shouldReturnConfiguredOpenAPI_WhenSwaggerBeanCreated() {

        SwaggerConfig config = new SwaggerConfig();

        OpenAPI api = config.customOpenAPI();

        assertAll(
                () -> assertNotNull(api),
                () -> assertEquals("FitReserve API", api.getInfo().getTitle()),
                () -> assertEquals("1.0", api.getInfo().getVersion())
        );
    }

    @Test
    void shouldNotThrow_WhenCorsConfigAddCorsMappingsCalled() {

        CorsConfig config = new CorsConfig();
        CorsRegistry registry = new CorsRegistry();

        assertDoesNotThrow(() -> config.addCorsMappings(registry));
    }
}
