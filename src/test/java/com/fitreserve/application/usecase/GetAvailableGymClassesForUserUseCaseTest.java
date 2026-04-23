package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.model.Reservation;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.repository.ReservationRepository;
import com.fitreserve.domain.valueobject.ClassId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.domain.model.ReservationStatus;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetAvailableGymClassesForUserUseCaseTest {

    private final GymClassRepository classRepository = mock(GymClassRepository.class);
    private final ReservationRepository reservationRepository = mock(ReservationRepository.class);
    private final GetAvailableGymClassesForUserUseCase useCase = new GetAvailableGymClassesForUserUseCase(
            classRepository, reservationRepository);

    @Test
    void shouldReturnClassesNotReservedByUser() {

        String userId = UUID.randomUUID().toString();

        GymClass class1 = mock(GymClass.class);
        GymClass class2 = mock(GymClass.class);

        ClassId class1Id = ClassId.generate();
        ClassId class2Id = ClassId.generate();
        ClassId sameValueAsClass1ButDifferentInstance = ClassId.from(class1Id.getValue());

        Reservation activeReservation = mock(Reservation.class);

        when(class1.getId()).thenReturn(class1Id);
        when(class2.getId()).thenReturn(class2Id);
        when(activeReservation.getClassId()).thenReturn(sameValueAsClass1ButDifferentInstance);
        when(activeReservation.getStatus()).thenReturn(com.fitreserve.domain.model.ReservationStatus.ACTIVE);

        when(classRepository.findAll()).thenReturn(List.of(class1, class2));
        when(reservationRepository.findByUserId(any()))
                .thenReturn(List.of(activeReservation));

        List<GymClass> result = useCase.execute(userId);

        assertEquals(1, result.size());
        assertEquals(class2Id, result.get(0).getId());
        verify(classRepository).findAll();
    }

    @Test
    void shouldReturnAllClasses_WhenUserHasNoReservations() {

        GymClass class1 = mock(GymClass.class);
        GymClass class2 = mock(GymClass.class);
        when(class1.getId()).thenReturn(ClassId.generate());
        when(class2.getId()).thenReturn(ClassId.generate());

        when(classRepository.findAll()).thenReturn(List.of(class1, class2));
        when(reservationRepository.findByUserId(any())).thenReturn(List.of());

        List<GymClass> result = useCase.execute(UUID.randomUUID().toString());

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnEmptyList_WhenAllClassesAreReservedByUser() {

        UserId userId = UserId.generate();
        ClassId classId = ClassId.generate();

        GymClass gymClass = mock(GymClass.class);
        when(gymClass.getId()).thenReturn(classId);

        Reservation activeReservation = mock(Reservation.class);
        when(activeReservation.getClassId()).thenReturn(ClassId.from(classId.getValue()));
        when(activeReservation.getStatus()).thenReturn(ReservationStatus.ACTIVE);

        when(classRepository.findAll()).thenReturn(List.of(gymClass));
        when(reservationRepository.findByUserId(any())).thenReturn(List.of(activeReservation));

        List<GymClass> result = useCase.execute(userId.getValue().toString());

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldIncludeClass_WhenUserHasOnlyCancelledReservationForIt() {

        UserId userId = UserId.generate();
        ClassId classId = ClassId.generate();

        GymClass gymClass = mock(GymClass.class);
        when(gymClass.getId()).thenReturn(classId);

        Reservation cancelledReservation = mock(Reservation.class);
        when(cancelledReservation.getClassId()).thenReturn(ClassId.from(classId.getValue()));
        when(cancelledReservation.getStatus()).thenReturn(ReservationStatus.CANCELLED);

        when(classRepository.findAll()).thenReturn(List.of(gymClass));
        when(reservationRepository.findByUserId(any())).thenReturn(List.of(cancelledReservation));

        List<GymClass> result = useCase.execute(userId.getValue().toString());

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyList_WhenNoClassesExist() {

        when(classRepository.findAll()).thenReturn(List.of());
        when(reservationRepository.findByUserId(any())).thenReturn(List.of());

        List<GymClass> result = useCase.execute(UUID.randomUUID().toString());

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenUserIdIsInvalidUUID() {

        assertThrows(IllegalArgumentException.class,
                () -> useCase.execute("not-a-uuid"));
    }
}