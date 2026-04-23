package com.fitreserve.infrastructure.persistence.repository;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.ReservationStatus;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.infrastructure.persistence.entity.ReservationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationRepositoryImplTest {

    @Mock
    private ReservationJpaRepository jpaRepository;

    @InjectMocks
    private ReservationRepositoryImpl repository;

    @Test
    void shouldReturnSavedReservation_WhenSaveCalled() {

        UUID id      = UUID.randomUUID();
        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        Reservation reservation = new Reservation(ReservationId.from(id), UserId.from(userId), ClassId.from(classId));
        ReservationEntity savedEntity = new ReservationEntity(id, userId, classId, "ACTIVE");
        when(jpaRepository.save(any())).thenReturn(savedEntity);

        Reservation result = repository.save(reservation);

        assertAll(
                () -> assertEquals(id, result.getId().getValue()),
                () -> assertEquals(ReservationStatus.ACTIVE, result.getStatus())
        );
    }

    @Test
    void shouldReturnReservation_WhenFindByIdCalledWithExistingId() {

        UUID id      = UUID.randomUUID();
        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        ReservationId reservationId = ReservationId.from(id);
        ReservationEntity entity = new ReservationEntity(id, userId, classId, "ACTIVE");
        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));

        Optional<Reservation> result = repository.findById(reservationId);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId().getValue());
    }

    @Test
    void shouldReturnEmpty_WhenFindByIdCalledWithNonExistentId() {

        UUID id = UUID.randomUUID();
        ReservationId reservationId = ReservationId.from(id);
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Reservation> result = repository.findById(reservationId);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnReservations_WhenFindByUserIdCalledWithMatchingUser() {

        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        UserId uid = UserId.from(userId);
        when(jpaRepository.findByUserId(userId)).thenReturn(List.of(
                new ReservationEntity(UUID.randomUUID(), userId, classId, "ACTIVE")
        ));

        List<Reservation> result = repository.findByUserId(uid);

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnTrue_WhenExistsByUserIdAndClassIdCalledWithMatchingEntry() {

        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        when(jpaRepository.existsByUserIdAndClassId(userId, classId)).thenReturn(true);

        boolean result = repository.existsByUserIdAndClassId(UserId.from(userId), ClassId.from(classId));

        assertTrue(result);
    }

    @Test
    void shouldReturnFalse_WhenExistsActiveByUserIdAndClassIdCalledWithNoActiveEntry() {

        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        when(jpaRepository.existsByUserIdAndClassIdAndStatus(userId, classId, "ACTIVE")).thenReturn(false);

        boolean result = repository.existsActiveByUserIdAndClassId(UserId.from(userId), ClassId.from(classId));

        assertFalse(result);
    }
}
