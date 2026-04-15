package com.fitreserve.application.usecase;

import com.fitreserve.domain.exception.NotFoundException;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.ClassId;

public class UpdateGymClassUseCase {

    private final GymClassRepository repository;

    public UpdateGymClassUseCase(GymClassRepository repository) {
        this.repository = repository;
    }

    public GymClass execute(String classIdRaw, String name) {

        GymClass gymClass = repository.findById(ClassId.fromString(classIdRaw))
                .orElseThrow(() -> new NotFoundException("Class not found"));

        gymClass.updateName(name);

        return repository.save(gymClass);
    }
}