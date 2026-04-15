package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.BusinessException;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.*;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String email, String password, String role) {

        Email userEmail = new Email(email);

        userRepository.findByEmail(userEmail)
                .ifPresent(u -> {
                    throw new BusinessException("Email already exists");
                });

        User user = new User(
                new UserId(UUID.randomUUID().toString()),
                userEmail,
                new Password(password),
                new Role(role)
        );

        return userRepository.save(user);
    }
}