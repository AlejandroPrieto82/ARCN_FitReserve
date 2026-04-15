package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.*;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.*;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(String email, String password, String roleRaw) {

        UserRole role = UserRole.valueOf(roleRaw.toUpperCase());

        User user = new User(
                new UserId(UUID.randomUUID()),
                new Email(email),
                new Password(password),
                role
        );

        return repository.save(user);
    }
}