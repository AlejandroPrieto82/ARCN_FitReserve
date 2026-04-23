package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.GymClassEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymClassRepositoryImplTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    @Mock
    private GymClassJpaRepository jpaRepository;

    @InjectMocks
    private GymClassRepositoryImpl repository;

    @Test
    void shouldReturnSavedGymClass_WhenSaveCalled() {

        UUID id = UUID.randomUUID();
        GymClass gymClass = new GymClass(ClassId.from(id), "Yoga", ClassType.YOGA,
                new TimeSlot(START, END), new Cupo(10));
        GymClassEntity savedEntity = new GymClassEntity(id, "Yoga", "YOGA", START, END, 10, 0);
        when(jpaRepository.save(any())).thenReturn(savedEntity);

        GymClass result = repository.save(gymClass);

        assertAll(
                () -> assertEquals(id, result.getId().getValue()),
                () -> assertEquals("Yoga", result.getName())
        );
        verify(jpaRepository).save(any());
    }

    @Test
    void shouldReturnGymClass_WhenFindByIdCalledWithExistingId() {

        UUID id = UUID.randomUUID();
        ClassId classId = ClassId.from(id);
        GymClassEntity entity = new GymClassEntity(id, "Yoga", "YOGA", START, END, 10, 0);
        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<GymClass> result = repository.findById(classId);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId().getValue());
    }

    @Test
    void shouldReturnEmpty_WhenFindByIdCalledWithNonExistentId() {

        UUID id = UUID.randomUUID();
        ClassId classId = ClassId.from(id);
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<GymClass> result = repository.findById(classId);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAllGymClasses_WhenFindAllCalled() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        when(jpaRepository.findAll()).thenReturn(List.of(
                new GymClassEntity(id1, "Yoga",   "YOGA",   START, END, 10, 0),
                new GymClassEntity(id2, "Cardio", "CARDIO", START, END,  8, 0)
        ));

        List<GymClass> result = repository.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void shouldDelegateToJpa_WhenDeleteByIdCalled() {

        UUID id = UUID.randomUUID();
        ClassId classId = ClassId.from(id);
        doNothing().when(jpaRepository).deleteById(id);

        repository.deleteById(classId);

        verify(jpaRepository).deleteById(id);
    }
}
