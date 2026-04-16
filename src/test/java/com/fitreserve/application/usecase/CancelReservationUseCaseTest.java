package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.ReservationRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CancelReservationUseCaseTest {

    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final CancelReservationUseCase useCase =
            new CancelReservationUseCase(reservationRepository);

    @Test
    void shouldCancelReservation() {

        String reservationId = UUID.randomUUID().toString();

        Reservation reservation = mock(Reservation.class);

        when(reservationRepository.findById(any()))
                .thenReturn(Optional.of(reservation));

        useCase.execute(reservationId);

        verify(reservation).cancel();
        verify(reservationRepository).save(any());
    }

    @Test
    void shouldThrowWhenNotFound() {

        String reservationId = UUID.randomUUID().toString();

        when(reservationRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.execute(reservationId));
    }
}