package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateGymClassUseCase;
import com.fitreserve.application.usecase.DeleteGymClassUseCase;
import com.fitreserve.application.usecase.GetAvailableGymClassesForUserUseCase;
import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.valueobject.*;
import com.fitreserve.interfaces.rest.request.CreateGymClassRequest;
import com.fitreserve.interfaces.rest.response.GymClassResponse;
import com.fitreserve.shared.util.ApiResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GymClassControllerTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 4, 21, 10, 0);
    private static final LocalDateTime END   = LocalDateTime.of(2026, 4, 21, 11, 0);

    @Mock
    private CreateGymClassUseCase createGymClassUseCase;
    @Mock
    private DeleteGymClassUseCase deleteGymClassUseCase;
    @Mock
    private GetAvailableGymClassesForUserUseCase getAvailableGymClassesForUserUseCase;

    @InjectMocks
    private GymClassController controller;

    @Test
    void shouldReturnAvailableClasses_WhenGetAvailableByUserCalled() {

        String userId = UUID.randomUUID().toString();
        GymClass gymClass = new GymClass(ClassId.generate(), "Yoga", ClassType.YOGA,
                new TimeSlot(START, END), new Cupo(10));
        when(getAvailableGymClassesForUserUseCase.execute(userId)).thenReturn(List.of(gymClass));

        ApiResponse<List<GymClassResponse>> result = controller.getAvailableByUser(userId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.getData().size())
        );
    }

    @Test
    void shouldReturnEmptyList_WhenGetAvailableByUserCalledWithNoClasses() {

        String userId = UUID.randomUUID().toString();
        when(getAvailableGymClassesForUserUseCase.execute(userId)).thenReturn(List.of());

        ApiResponse<List<GymClassResponse>> result = controller.getAvailableByUser(userId);

        assertTrue(result.getData().isEmpty());
    }

    @Test
    void shouldReturnCreatedClass_WhenCreateCalled() {

        GymClass gymClass = new GymClass(ClassId.generate(), "Yoga", ClassType.YOGA,
                new TimeSlot(START, END), new Cupo(10));
        when(createGymClassUseCase.execute("Yoga", "YOGA", "2026-04-21T10:00", "2026-04-21T11:00", 10))
                .thenReturn(gymClass);
        CreateGymClassRequest request = new CreateGymClassRequest(
                "Yoga", "YOGA", "2026-04-21T10:00", "2026-04-21T11:00", 10);

        ApiResponse<GymClassResponse> result = controller.create(request);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Yoga", result.getData().getName())
        );
    }

    @Test
    void shouldReturnDeletedMessage_WhenDeleteCalled() {

        String classId = UUID.randomUUID().toString();
        doNothing().when(deleteGymClassUseCase).execute(classId);

        ApiResponse<String> result = controller.delete(classId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Class deleted", result.getData())
        );
        verify(deleteGymClassUseCase).execute(classId);
    }
}
