package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fitreserve.domain.model.ClassType;

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

    @Test
    void shouldCreateGymClass_WhenCapacityIsMinimumBoundary() {

        when(repository.save(any(GymClass.class))).thenAnswer(inv -> inv.getArgument(0));

        GymClass result = useCase.execute("CrossFit", "CROSSFIT",
                "2026-04-15T10:00:00", "2026-04-15T11:00:00", 1);

        assertEquals(1, result.getCapacity().getValue());
    }

    @Test
    void shouldCreateGymClass_WhenTypeIsLowercase() {

        when(repository.save(any(GymClass.class))).thenAnswer(inv -> inv.getArgument(0));

        GymClass result = useCase.execute("Cardio Blast", "cardio",
                "2026-04-15T10:00:00", "2026-04-15T11:00:00", 10);

        assertEquals(ClassType.CARDIO, result.getType());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenCapacityIsZero() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("Class", "YOGA",
                        "2026-04-15T10:00:00", "2026-04-15T11:00:00", 0));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenCapacityIsNegative() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("Class", "YOGA",
                        "2026-04-15T10:00:00", "2026-04-15T11:00:00", -5));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEndIsBeforeStart() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("Class", "YOGA",
                        "2026-04-15T11:00:00", "2026-04-15T10:00:00", 10));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenStartEqualsEnd() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("Class", "YOGA",
                        "2026-04-15T10:00:00", "2026-04-15T10:00:00", 10));
    }
}