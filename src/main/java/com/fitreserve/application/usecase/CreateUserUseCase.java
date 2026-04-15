package com.fitreserve.application.usecase;

import com.fitreserve.application.dto.CreateUserRequest;
import com.fitreserve.application.dto.UserResponse;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.model.UserRole;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.*;

import java.util.UUID;

public class CreateUserUseCase {

    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse execute(CreateUserRequest request) {

        UserRole role = UserRole.valueOf(request.getRole().toUpperCase());

        User user = new User(
                new UserId(UUID.randomUUID()),
                new Email(request.getEmail()),
                new Password(request.getPassword()),
                role
        );

        repository.save(user);

        return new UserResponse(
                user.getId().getValue().toString(),
                user.getEmail().getValue(),
                user.getRole().name(),
                user.isActive()
        );
    }
}