package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateGymClassUseCase;
import com.fitreserve.application.usecase.DeleteGymClassUseCase;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.shared.util.ApiResponse;
import com.fitreserve.interfaces.rest.request.CreateGymClassRequest;
import com.fitreserve.interfaces.rest.response.GymClassResponse;

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
    public ApiResponse<GymClassResponse> create(@RequestBody CreateGymClassRequest request) {

        GymClass gymClass = createGymClassUseCase.execute(
                request.getName(),
                request.getType(),
                request.getStartTime(),
                request.getEndTime(),
                request.getCapacity()
        );

        return ApiResponse.of(GymClassResponse.from(gymClass));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        deleteGymClassUseCase.execute(id);
        return ApiResponse.of("Class deleted");
    }
}