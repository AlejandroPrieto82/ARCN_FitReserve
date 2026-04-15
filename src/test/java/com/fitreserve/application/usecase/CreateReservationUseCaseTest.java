package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.interfaces.rest.request.CreateReservationRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GymClassRepository classRepository;

    @InjectMocks
    private CreateReservationUseCase useCase;

    @Test
    void shouldCreateReservationSuccessfully() {

        CreateReservationRequest request =
                new CreateReservationRequest(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                );

        User user = mock(User.class);
        GymClass gymClass = mock(GymClass.class);

        when(user.isActive()).thenReturn(true);
        when(gymClass.hasAvailableSpots()).thenReturn(true);

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(classRepository.findById(any())).thenReturn(Optional.of(gymClass));
        when(reservationRepository.existsByUserIdAndClassId(any(), any()))
                .thenReturn(false);

        when(reservationRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        var response = useCase.execute(request);

        assertNotNull(response);
        assertEquals("Reservation created", response.getMessage());

        verify(reservationRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowWhenUserNotFound() {

        CreateReservationRequest request =
                new CreateReservationRequest(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                );

        when(userRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.execute(request));

        verify(reservationRepository, never()).save(any());
    }

    @Test
    void shouldThrowWhenUserInactive() {

        CreateReservationRequest request =
                new CreateReservationRequest(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                );

        User user = mock(User.class);
        when(user.isActive()).thenReturn(false);

        when(userRepository.findById(any()))
                .thenReturn(Optional.of(user));

        assertThrows(BusinessException.class,
                () -> useCase.execute(request));

        verify(reservationRepository, never()).save(any());
    }

    @Test
    void shouldThrowWhenClassFull() {

        CreateReservationRequest request =
                new CreateReservationRequest(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                );

        User user = mock(User.class);
        GymClass gymClass = mock(GymClass.class);

        when(user.isActive()).thenReturn(true);
        when(gymClass.hasAvailableSpots()).thenReturn(false);

        when(userRepository.findById(any()))
                .thenReturn(Optional.of(user));

        when(classRepository.findById(any()))
                .thenReturn(Optional.of(gymClass));

        assertThrows(BusinessException.class,
                () -> useCase.execute(request));

        verify(reservationRepository, never()).save(any());
    }

    @Test
    void shouldThrowWhenDuplicateReservation() {

        CreateReservationRequest request =
                new CreateReservationRequest(
                        UUID.randomUUID().toString(),
                        UUID.randomUUID().toString()
                );

        User user = mock(User.class);
        GymClass gymClass = mock(GymClass.class);

        when(user.isActive()).thenReturn(true);
        when(gymClass.hasAvailableSpots()).thenReturn(true);

        when(userRepository.findById(any()))
                .thenReturn(Optional.of(user));

        when(classRepository.findById(any()))
                .thenReturn(Optional.of(gymClass));

        when(reservationRepository.existsByUserIdAndClassId(any(), any()))
                .thenReturn(true);

        assertThrows(BusinessException.class,
                () -> useCase.execute(request));

        verify(reservationRepository, never()).save(any());
    }
}