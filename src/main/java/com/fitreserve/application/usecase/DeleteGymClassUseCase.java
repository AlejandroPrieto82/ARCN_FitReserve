package com.fitreserve.application.usecase;

import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.ClassId;

public class DeleteGymClassUseCase {

    private final GymClassRepository repository;

    public DeleteGymClassUseCase(GymClassRepository repository) {
        this.repository = repository;
    }

    public void execute(String classId) {
        repository.deleteById(ClassId.fromString(classId));
    }
}