package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.model.UserRole;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Password;
import com.fitreserve.domain.valueobject.UserId;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(String email, String password, String role) {

        UserRole userRole = UserRole.valueOf(role.toUpperCase());

        User user = new User(
                new UserId(UUID.randomUUID()),
                new Email(email),
                new Password(password),
                userRole
        );

        return repository.save(user);
    }
}