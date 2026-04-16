package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateGymClassUseCaseTest {

    private final GymClassRepository repository = mock(GymClassRepository.class);
    private final CreateGymClassUseCase useCase = new CreateGymClassUseCase(repository);

    @Test
    void shouldCreateGymClassSuccessfully() {

        GymClass gymClass = mock(GymClass.class);

        when(repository.save(any(GymClass.class))).thenReturn(gymClass);

        GymClass result = useCase.execute(
                "Yoga",
                "YOGA",
                "2026-04-15T10:00:00",
                "2026-04-15T11:00:00",
                10
        );

        assertNotNull(result);
        verify(repository).save(any(GymClass.class));
    }

    @Test
    void shouldFailWhenInvalidType() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute(
                        "Yoga",
                        "INVALID_TYPE",
                        "2026-04-15T10:00:00",
                        "2026-04-15T11:00:00",
                        10
                ));
    }
}