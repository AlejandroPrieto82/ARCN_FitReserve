package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.model.User;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.UserId;

public class GetUserByIdUseCase {

    private final UserRepository repository;

    public GetUserByIdUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User execute(String userIdRaw) {
        return repository.findById(UserId.fromString(userIdRaw))
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}