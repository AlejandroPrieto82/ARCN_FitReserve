package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.exception.ValidationException;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;

public class LoginUseCase {

    private final UserRepository repository;

    public LoginUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(String email, String password) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!user.isActive()) {
            throw new ValidationException("User is inactive");
        }

        if (!user.getPassword().getValue().equals(password)) {
            throw new ValidationException("Invalid password");
        }

        return user;
    }
}
