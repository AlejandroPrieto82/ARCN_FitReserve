package com.fitreserve.infrastructure.config;

import com.fitreserve.application.usecase.*;
import com.fitreserve.domain.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }

    @Bean
    public DeactivateUserUseCase deactivateUserUseCase(UserRepository userRepository) {
        return new DeactivateUserUseCase(userRepository);
    }

    @Bean
    public CreateGymClassUseCase createGymClassUseCase(GymClassRepository repository) {
        return new CreateGymClassUseCase(repository);
    }

    @Bean
    public CreateReservationUseCase createReservationUseCase(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            GymClassRepository classRepository) {

        return new CreateReservationUseCase(
                reservationRepository,
                userRepository,
                classRepository
        );
    }

    @Bean
    public CancelReservationUseCase cancelReservationUseCase(
            ReservationRepository repository) {

        return new CancelReservationUseCase(repository);
    }

    @Bean
    public GetUserReservationsUseCase getUserReservationsUseCase(
            ReservationRepository repository) {

        return new GetUserReservationsUseCase(repository);
    }
}