package com.fitreserve.application.usecase;

import com.fitreserve.application.dto.CreateReservationRequest;
import com.fitreserve.application.dto.CreateReservationResponse;
import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.exception.ValidationException;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.model.ReservationStatus;
import com.fitreserve.domain.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private CreateReservationUseCase createReservationUseCase;

    @Test
    void shouldCreateReservationSuccessfully() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                "user-1",
                "session-1",
                true,
                false,
                true
        );
        when(reservationRepository.save(any(Reservation.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CreateReservationResponse response = createReservationUseCase.execute(request);

        // Assert
        assertNotNull(response);
        assertEquals(ReservationStatus.ACTIVE, response.getStatus());
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void shouldThrowExceptionWhenUserIsNotActive() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                "user-1",
                "session-1",
                false,
                false,
                true
        );

        // Act
        assertThrows(BusinessException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void shouldThrowExceptionWhenDuplicateReservation() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                "user-1",
                "session-1",
                true,
                true,
                true
        );

        // Act
        assertThrows(BusinessException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void shouldThrowExceptionWhenNoCapacity() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                "user-1",
                "session-1",
                true,
                false,
                false
        );

        // Act
        assertThrows(BusinessException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void shouldThrowValidationExceptionWhenRequestIsNull() {
        // Arrange
        CreateReservationRequest request = null;

        // Act
        assertThrows(ValidationException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void shouldThrowValidationExceptionWhenUserIdIsNull() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                null,
                "session-1",
                true,
                false,
                true
        );

        // Act
        assertThrows(ValidationException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void shouldThrowValidationExceptionWhenUserIdIsBlank() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                "   ",
                "session-1",
                true,
                false,
                true
        );

        // Act
        assertThrows(ValidationException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void shouldThrowValidationExceptionWhenSessionIdIsNull() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                "user-1",
                null,
                true,
                false,
                true
        );

        // Act
        assertThrows(ValidationException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }

    @Test
    void shouldThrowValidationExceptionWhenSessionIdIsBlank() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest(
                "user-1",
                "   ",
                true,
                false,
                true
        );

        // Act
        assertThrows(ValidationException.class, () -> createReservationUseCase.execute(request));

        // Assert
        verify(reservationRepository, never()).save(any(Reservation.class));
    }
}
