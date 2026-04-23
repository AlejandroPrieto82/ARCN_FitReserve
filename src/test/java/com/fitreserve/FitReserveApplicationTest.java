package com.fitreserve;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FitReserveApplicationTest {

    @Test
    void shouldBeInstantiable_WhenDefaultConstructorCalled() {

        assertDoesNotThrow(() -> new FitReserveApplication());
    }

    @Test
    void shouldCallSpringApplicationRun_WhenMainCalled() {

        try (MockedStatic<SpringApplication> mocked = mockStatic(SpringApplication.class)) {

            FitReserveApplication.main(new String[]{});

            mocked.verify(() -> SpringApplication.run(FitReserveApplication.class, new String[]{}));
        }
    }
}
