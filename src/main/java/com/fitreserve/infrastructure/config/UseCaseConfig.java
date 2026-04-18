package com.fitreserve.infrastructure.config;

import com.fitreserve.application.usecase.*;
import com.fitreserve.domain.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository repo) {
        return new CreateUserUseCase(repo);
    }

    @Bean
    public DeactivateUserUseCase deactivateUserUseCase(UserRepository repo) {
        return new DeactivateUserUseCase(repo);
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepository repo) {
        return new LoginUseCase(repo);
    }

    @Bean
    public CreateGymClassUseCase createGymClassUseCase(GymClassRepository repo) {
        return new CreateGymClassUseCase(repo);
    }

    @Bean
    public DeleteGymClassUseCase deleteGymClassUseCase(GymClassRepository repo) {
        return new DeleteGymClassUseCase(repo);
    }

    @Bean
    public UpdateGymClassUseCase updateGymClassUseCase(GymClassRepository repo) {
        return new UpdateGymClassUseCase(repo);
    }

    @Bean
    public CreateReservationUseCase createReservationUseCase(
            ReservationRepository reservationRepo,
            UserRepository userRepo,
            GymClassRepository classRepo) {
        return new CreateReservationUseCase(reservationRepo, userRepo, classRepo);
    }

    @Bean
    public CancelReservationUseCase cancelReservationUseCase(ReservationRepository repo) {
        return new CancelReservationUseCase(repo);
    }

    @Bean
    public GetUserReservationsUseCase getUserReservationsUseCase(ReservationRepository repo) {
        return new GetUserReservationsUseCase(repo);
    }
}