package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateUserUseCase;
import com.fitreserve.application.usecase.DeactivateUserUseCase;
import com.fitreserve.application.usecase.LoginUseCase;
import com.fitreserve.domain.model.User;
import com.fitreserve.shared.util.ApiResponse;
import com.fitreserve.interfaces.rest.request.CreateUserRequest;
import com.fitreserve.interfaces.rest.request.LoginRequest;
import com.fitreserve.interfaces.rest.response.UserResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final DeactivateUserUseCase deactivateUserUseCase;
    private final LoginUseCase loginUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
            DeactivateUserUseCase deactivateUserUseCase,
            LoginUseCase loginUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.deactivateUserUseCase = deactivateUserUseCase;
        this.loginUseCase = loginUseCase;
    }

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody CreateUserRequest request) {

        User user = createUserUseCase.execute(
                request.getEmail(),
                request.getPassword(),
                request.getRole());

        return ApiResponse.of(UserResponse.from(user));
    }

    @PostMapping("/login")
    public ApiResponse<UserResponse> login(@RequestBody LoginRequest request) {
        User user = loginUseCase.execute(request.getEmail(), request.getPassword());
        return ApiResponse.of(UserResponse.from(user));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deactivate(@PathVariable String id) {
        deactivateUserUseCase.execute(id);
        return ApiResponse.of("User deactivated");
    }
}