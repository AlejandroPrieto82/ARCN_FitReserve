package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateUserUseCase;
import com.fitreserve.application.usecase.DeactivateUserUseCase;
import com.fitreserve.interfaces.rest.request.CreateUserRequest;
import com.fitreserve.interfaces.rest.response.UserResponse;
import com.fitreserve.shared.util.ApiResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final DeactivateUserUseCase deactivateUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          DeactivateUserUseCase deactivateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deactivateUserUseCase = deactivateUserUseCase;
    }

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody CreateUserRequest request) {
        return ApiResponse.of(createUserUseCase.execute(request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deactivate(@PathVariable String id) {
        deactivateUserUseCase.execute(id);
        return ApiResponse.of("User deactivated");
    }
}