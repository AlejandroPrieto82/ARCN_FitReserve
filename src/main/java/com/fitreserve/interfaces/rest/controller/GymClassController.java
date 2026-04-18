package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateGymClassUseCase;
import com.fitreserve.application.usecase.DeleteGymClassUseCase;
import com.fitreserve.application.usecase.GetAvailableGymClassesForUserUseCase;
import com.fitreserve.domain.model.GymClass;
import com.fitreserve.shared.util.ApiResponse;
import com.fitreserve.interfaces.rest.request.CreateGymClassRequest;
import com.fitreserve.interfaces.rest.response.GymClassResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class GymClassController {

    private final CreateGymClassUseCase createGymClassUseCase;
    private final DeleteGymClassUseCase deleteGymClassUseCase;
    private final GetAvailableGymClassesForUserUseCase getAvailableGymClassesForUserUseCase;

    public GymClassController(CreateGymClassUseCase createGymClassUseCase,
            DeleteGymClassUseCase deleteGymClassUseCase,
            GetAvailableGymClassesForUserUseCase getAvailableGymClassesForUserUseCase) {
        this.createGymClassUseCase = createGymClassUseCase;
        this.deleteGymClassUseCase = deleteGymClassUseCase;
        this.getAvailableGymClassesForUserUseCase = getAvailableGymClassesForUserUseCase;
    }

    @GetMapping
    public ApiResponse<List<GymClassResponse>> getAvailableByUser(@RequestParam String userId) {
        return ApiResponse.of(
                getAvailableGymClassesForUserUseCase.execute(userId)
                        .stream()
                        .map(GymClassResponse::from)
                        .toList());
    }

    @PostMapping
    public ApiResponse<GymClassResponse> create(@RequestBody CreateGymClassRequest request) {

        GymClass gymClass = createGymClassUseCase.execute(
                request.getName(),
                request.getType(),
                request.getStartTime(),
                request.getEndTime(),
                request.getCapacity());

        return ApiResponse.of(GymClassResponse.from(gymClass));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        deleteGymClassUseCase.execute(id);
        return ApiResponse.of("Class deleted");
    }
}