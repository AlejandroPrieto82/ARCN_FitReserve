package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.*;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.*;

public class CreateGymClassUseCase {

    private final GymClassRepository repository;

    public CreateGymClassUseCase(GymClassRepository repository) {
        this.repository = repository;
    }

    public GymClass execute(String name, String type, String start, String end, int capacity) {

        GymClass gymClass = new GymClass(
                ClassId.generate(),
                name,
                ClassType.valueOf(type.toUpperCase()),
                TimeSlot.fromStrings(start, end),
                new Cupo(capacity)
        );

        return repository.save(gymClass);
    }
}