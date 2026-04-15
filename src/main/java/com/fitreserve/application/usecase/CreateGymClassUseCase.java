package com.fitreserve.application.usecase;

import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.ClassId;
import com.fitreserve.domain.valueobject.Cupo;
import com.fitreserve.domain.valueobject.TimeSlot;

import java.util.UUID;

public class CreateGymClassUseCase {

    private final GymClassRepository repository;

    public CreateGymClassUseCase(GymClassRepository repository) {
        this.repository = repository;
    }

    public GymClass execute(String name, String type, String start, String end, int capacity) {

        GymClass gymClass = new GymClass(
                new ClassId(UUID.randomUUID()),
                name,
                ClassType.valueOf(type.toUpperCase()),
                TimeSlot.fromStrings(start, end),
                new Cupo(capacity)
        );

        return repository.save(gymClass);
    }
}