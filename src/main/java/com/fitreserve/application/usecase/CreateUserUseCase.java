package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.Email;
import com.fitreserve.domain.valueobject.Password;
import com.fitreserve.domain.valueobject.UserId;
import com.fitreserve.domain.model.UserRole;
import com.fitreserve.interfaces.rest.request.CreateUserRequest;
import com.fitreserve.interfaces.rest.response.UserResponse;

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