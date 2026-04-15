package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateGymClassUseCase;
import com.fitreserve.application.usecase.DeleteGymClassUseCase;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.shared.util.ApiResponse;
import com.fitreserve.interfaces.rest.request.CreateGymClassRequest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classes")
public class GymClassController {

    private final CreateGymClassUseCase createGymClassUseCase;
    private final DeleteGymClassUseCase deleteGymClassUseCase;

    public GymClassController(CreateGymClassUseCase createGymClassUseCase,
                              DeleteGymClassUseCase deleteGymClassUseCase) {
        this.createGymClassUseCase = createGymClassUseCase;
        this.deleteGymClassUseCase = deleteGymClassUseCase;
    }

    @PostMapping
    public ApiResponse<GymClass> create(@RequestBody CreateGymClassRequest request) {

        GymClass created = createGymClassUseCase.execute(
                request.getName(),
                request.getType(),
                request.getStartTime(),
                request.getEndTime(),
                request.getCapacity()
        );

        return new ApiResponse<>(created);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        deleteGymClassUseCase.execute(id);
        return new ApiResponse<>("Class deleted");
    }
}