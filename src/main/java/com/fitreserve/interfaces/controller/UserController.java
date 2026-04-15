package com.fitreserve.interfaces.rest.controller;

import com.fitreserve.application.usecase.CreateUserUseCase;
import com.fitreserve.application.usecase.DeactivateUserUseCase;
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
    public ApiResponse<?> createUser(@RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String role) {

        return new ApiResponse<>(createUserUseCase.execute(email, password, role));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deactivate(@PathVariable String id) {
        deactivateUserUseCase.execute(id);
        return new ApiResponse<>("User deactivated");
    }
}