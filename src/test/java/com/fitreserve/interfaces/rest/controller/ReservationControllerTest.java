package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CancelReservationUseCase;
import com.fitreserve.application.usecase.CreateReservationUseCase;
import com.fitreserve.application.usecase.GetUserReservationsUseCase;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.interfaces.rest.request.CreateReservationRequest;
import com.fitreserve.interfaces.rest.response.ReservationResponse;
import com.fitreserve.shared.util.ApiResponse;
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
class ReservationControllerTest {

    @Mock
    private CreateReservationUseCase createReservationUseCase;
    @Mock
    private CancelReservationUseCase cancelReservationUseCase;
    @Mock
    private GetUserReservationsUseCase getUserReservationsUseCase;
    @Mock
    private GymClassRepository classRepository;

    @InjectMocks
    private ReservationController controller;

    @Test
    void shouldReturnCreatedReservation_WhenCreateCalled() {

        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        Reservation reservation = new Reservation(
                ReservationId.generate(), UserId.from(userId), ClassId.from(classId));
        when(createReservationUseCase.execute(userId.toString(), classId.toString())).thenReturn(reservation);
        when(classRepository.findById(any())).thenReturn(Optional.empty());
        CreateReservationRequest request = new CreateReservationRequest(userId.toString(), classId.toString());

        ApiResponse<ReservationResponse> result = controller.create(request);

        assertNotNull(result);
        verify(createReservationUseCase).execute(userId.toString(), classId.toString());
    }

    @Test
    void shouldReturnCancelledMessage_WhenCancelCalled() {

        String reservationId = UUID.randomUUID().toString();
        doNothing().when(cancelReservationUseCase).execute(reservationId);

        ApiResponse<String> result = controller.cancel(reservationId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Reservation cancelled", result.getData())
        );
        verify(cancelReservationUseCase).execute(reservationId);
    }

    @Test
    void shouldReturnUserReservations_WhenGetByUserCalled() {

        UUID userId  = UUID.randomUUID();
        UUID classId = UUID.randomUUID();
        Reservation reservation = new Reservation(
                ReservationId.generate(), UserId.from(userId), ClassId.from(classId));
        when(getUserReservationsUseCase.execute(userId.toString())).thenReturn(List.of(reservation));
        when(classRepository.findById(any())).thenReturn(Optional.empty());

        ApiResponse<List<ReservationResponse>> result = controller.getByUser(userId.toString());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.getData().size())
        );
    }
}
