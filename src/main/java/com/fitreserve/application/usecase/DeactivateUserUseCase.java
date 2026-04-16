package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.repository.UserRepository;
import com.fitreserve.domain.valueobject.UserId;

public class DeactivateUserUseCase {

    private final UserRepository repository;

    public DeactivateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String userIdRaw) {

        var user = repository.findById(UserId.fromString(userIdRaw))
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.deactivate();

        repository.save(user);
    }
}