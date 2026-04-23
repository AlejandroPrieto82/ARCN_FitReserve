package com.fitreserve.infrastructure.config;

import com.fitreserve.application.usecase.*;
import com.fitreserve.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UseCaseConfigTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private GymClassRepository gymClassRepository;
    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private UseCaseConfig config;

    @Test
    void shouldReturnCreateUserUseCase_WhenBeanCreated() {

        CreateUserUseCase useCase = config.createUserUseCase(userRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnDeactivateUserUseCase_WhenBeanCreated() {

        DeactivateUserUseCase useCase = config.deactivateUserUseCase(userRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnLoginUseCase_WhenBeanCreated() {

        LoginUseCase useCase = config.loginUseCase(userRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnCreateGymClassUseCase_WhenBeanCreated() {

        CreateGymClassUseCase useCase = config.createGymClassUseCase(gymClassRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnDeleteGymClassUseCase_WhenBeanCreated() {

        DeleteGymClassUseCase useCase = config.deleteGymClassUseCase(gymClassRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnUpdateGymClassUseCase_WhenBeanCreated() {

        UpdateGymClassUseCase useCase = config.updateGymClassUseCase(gymClassRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnGetAvailableGymClassesUseCase_WhenBeanCreated() {

        GetAvailableGymClassesForUserUseCase useCase =
                config.getAvailableGymClassesForUserUseCase(gymClassRepository, reservationRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnCreateReservationUseCase_WhenBeanCreated() {

        CreateReservationUseCase useCase =
                config.createReservationUseCase(reservationRepository, userRepository, gymClassRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnCancelReservationUseCase_WhenBeanCreated() {

        CancelReservationUseCase useCase =
                config.cancelReservationUseCase(reservationRepository, gymClassRepository);

        assertNotNull(useCase);
    }

    @Test
    void shouldReturnGetUserReservationsUseCase_WhenBeanCreated() {

        GetUserReservationsUseCase useCase =
                config.getUserReservationsUseCase(reservationRepository);

        assertNotNull(useCase);
    }
}
