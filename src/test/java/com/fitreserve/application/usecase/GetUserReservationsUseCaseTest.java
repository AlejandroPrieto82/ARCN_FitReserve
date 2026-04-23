package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.ReservationId;
import com.fitreserve.domain.valueobject.UserId;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GetUserReservationsUseCaseTest {

    private final ReservationRepository repository = mock(ReservationRepository.class);
    private final GetUserReservationsUseCase useCase = new GetUserReservationsUseCase(repository);

    @Test
    void shouldReturnReservations_WhenUserHasReservations() {

        UserId userId = UserId.generate();
        Reservation r1 = new Reservation(ReservationId.generate(), userId, ClassId.generate());
        Reservation r2 = new Reservation(ReservationId.generate(), userId, ClassId.generate());

        when(repository.findByUserId(any())).thenReturn(List.of(r1, r2));

        List<Reservation> result = useCase.execute(userId.getValue().toString());

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertTrue(result.contains(r1)),
                () -> assertTrue(result.contains(r2)),
                () -> verify(repository).findByUserId(any())
        );
    }

    @Test
    void shouldReturnEmptyList_WhenUserHasNoReservations() {

        UserId userId = UserId.generate();

        when(repository.findByUserId(any())).thenReturn(List.of());

        List<Reservation> result = useCase.execute(userId.getValue().toString());

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenUserIdIsInvalidUUID() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("invalid-uuid"));
    }
}
