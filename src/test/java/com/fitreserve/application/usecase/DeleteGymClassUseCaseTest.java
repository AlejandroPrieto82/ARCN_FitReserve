package com.fitreserve.application.usecase;

import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.ClassId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeleteGymClassUseCaseTest {

    private final GymClassRepository repository = mock(GymClassRepository.class);
    private final DeleteGymClassUseCase useCase = new DeleteGymClassUseCase(repository);

    @Test
    void shouldDeleteGymClassSuccessfully() {

        UUID id = UUID.randomUUID();

        doNothing().when(repository).deleteById(any());

        assertDoesNotThrow(() -> useCase.execute(id.toString()));

        verify(repository).deleteById(any());
    }

    @Test
    void shouldFailWhenIdInvalid() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("invalid-uuid"));
    }
}