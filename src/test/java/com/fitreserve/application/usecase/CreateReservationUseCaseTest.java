package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.*;
import com.fitreserve.domain.repository.*;
import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateReservationUseCaseTest {

    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final GymClassRepository gymClassRepository = mock(GymClassRepository.class);

    private final CreateReservationUseCase useCase =
            new CreateReservationUseCase(
                    reservationRepository,
                    userRepository,
                    gymClassRepository
            );

    @Test
    void shouldCreateReservationSuccessfully() {

        String userId = UUID.randomUUID().toString();
        String classId = UUID.randomUUID().toString();

        User user = mock(User.class);
        when(user.isActive()).thenReturn(true);

        GymClass gymClass = mock(GymClass.class);
        when(gymClass.hasAvailableSpots()).thenReturn(true);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(gymClassRepository.findById(any())).thenReturn(Optional.of(gymClass));
        when(reservationRepository.existsActiveByUserIdAndClassId(any(), any())).thenReturn(false);

        Reservation result = useCase.execute(userId, classId);

        assertNotNull(result);
        verify(reservationRepository).save(any());
        verify(gymClassRepository).save(any());
    }

    @Test
    void shouldFailWhenUserNotFound() {

        String userId = UUID.randomUUID().toString();
        String classId = UUID.randomUUID().toString();

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> useCase.execute(userId, classId));
    }

    @Test
    void shouldFailWhenUserInactive() {

        String userId = UUID.randomUUID().toString();
        String classId = UUID.randomUUID().toString();

        User user = mock(User.class);
        when(user.isActive()).thenReturn(false);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        assertThrows(BusinessException.class,
                () -> useCase.execute(userId, classId));
    }

    @Test
    void shouldFailWhenClassFull() {

        String userId = UUID.randomUUID().toString();
        String classId = UUID.randomUUID().toString();

        User user = mock(User.class);
        when(user.isActive()).thenReturn(true);

        GymClass gymClass = mock(GymClass.class);
        when(gymClass.hasAvailableSpots()).thenReturn(false);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(gymClassRepository.findById(any())).thenReturn(Optional.of(gymClass));

        assertThrows(BusinessException.class,
                () -> useCase.execute(userId, classId));
    }

    @Test
    void shouldFailWhenDuplicateReservation() {

        String userId = UUID.randomUUID().toString();
        String classId = UUID.randomUUID().toString();

        User user = mock(User.class);
        when(user.isActive()).thenReturn(true);

        GymClass gymClass = mock(GymClass.class);
        when(gymClass.hasAvailableSpots()).thenReturn(true);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(gymClassRepository.findById(any())).thenReturn(Optional.of(gymClass));
        when(reservationRepository.existsActiveByUserIdAndClassId(any(), any())).thenReturn(true);

        assertThrows(BusinessException.class,
                () -> useCase.execute(userId, classId));
    }
}