package com.fitreserve.application.usecase;

import com.fitreserve.application.dto.CreateGymClassRequest;
import com.fitreserve.application.dto.GymClassResponse;
import com.fitreserve.domain.model.ClassType;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.domain.repository.GymClassRepository;
import com.fitreserve.domain.valueobject.*;

import java.util.UUID;

public class CreateGymClassUseCase {

    private final GymClassRepository repository;

    public CreateGymClassUseCase(GymClassRepository repository) {
        this.repository = repository;
    }

    public GymClassResponse execute(CreateGymClassRequest request) {

        TimeSlot timeSlot = TimeSlot.fromStrings(
                request.getStartTime(),
                request.getEndTime()
        );

        GymClass gymClass = new GymClass(
                new ClassId(UUID.randomUUID()),
                request.getName(),
                ClassType.valueOf(request.getType().toUpperCase()),
                timeSlot,
                new Cupo(request.getCapacity())
        );

        repository.save(gymClass);

        return new GymClassResponse(
                gymClass.getId().getValue().toString(),
                gymClass.getName(),
                gymClass.getType().name(),
                gymClass.getTimeSlot().getStart().toString(),
                gymClass.getTimeSlot().getEnd().toString(),
                gymClass.getCapacity().getValue(),
                0
        );
    }
}