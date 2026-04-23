package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.ClassId;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateGymClassUseCaseTest {

    private final GymClassRepository repository = mock(GymClassRepository.class);
    private final UpdateGymClassUseCase useCase = new UpdateGymClassUseCase(repository);

    @Test
    void shouldUpdateGymClassSuccessfully() {

        GymClass gymClass = mock(GymClass.class);
        ClassId id = ClassId.from(UUID.randomUUID());

        when(repository.findById(any())).thenReturn(Optional.of(gymClass));
        when(repository.save(any())).thenReturn(gymClass);

        GymClass result = useCase.execute(
                id.value(),
                "Updated Name"
        );

        assertNotNull(result);
        verify(repository).save(any(GymClass.class));
    }

    @Test
    void shouldFailWhenClassNotFound() {

        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.execute(
                        UUID.randomUUID().toString(),
                        "Updated Name"
                ));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenClassIdIsInvalidUUID() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("not-a-uuid", "Updated Name"));
    }
}