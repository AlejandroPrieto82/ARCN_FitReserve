package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.ClassId;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.fitreserve.domain.exception.NotFoundException;

class CancelReservationUseCaseTest {

    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final GymClassRepository gymClassRepository = mock(GymClassRepository.class);
    private final CancelReservationUseCase useCase =
            new CancelReservationUseCase(reservationRepository, gymClassRepository);

    @Test
    void shouldCancelReservation() {

        String reservationId = UUID.randomUUID().toString();

        Reservation reservation = mock(Reservation.class);
        GymClass gymClass = mock(GymClass.class);

        when(reservationRepository.findById(any()))
                .thenReturn(Optional.of(reservation));
        when(reservation.getClassId()).thenReturn(ClassId.generate());
        when(gymClassRepository.findById(any()))
                .thenReturn(Optional.of(gymClass));

        useCase.execute(reservationId);

        verify(reservation).cancel();
        verify(reservationRepository).save(any());
        verify(gymClass).unreserveSpot();
        verify(gymClassRepository).save(any());
    }

    @Test
    void shouldThrowWhenNotFound() {

        String reservationId = UUID.randomUUID().toString();

        when(reservationRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.execute(reservationId));
    }

    @Test
    void shouldThrowNotFoundException_WhenGymClassDoesNotExist() {

        String reservationId = UUID.randomUUID().toString();
        Reservation reservation = mock(Reservation.class);

        when(reservationRepository.findById(any())).thenReturn(Optional.of(reservation));
        when(reservation.getClassId()).thenReturn(ClassId.generate());
        when(gymClassRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> useCase.execute(reservationId));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenReservationIdIsInvalidUUID() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("not-a-uuid"));
    }
}