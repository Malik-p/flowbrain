package com.flowbrain.backend.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowbrain.backend.auth.dto.LoginRequest;
import com.flowbrain.backend.auth.dto.LoginResponse;
import com.flowbrain.backend.auth.dto.RegisterRequest;
import com.flowbrain.backend.auth.dto.UserResponse;
import com.flowbrain.backend.auth.service.AuthService;
import com.flowbrain.backend.common.dto.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@Valid @RequestBody RegisterRequest request) {

        UserResponse response = authService.register(request);

        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(
                true,
                "User registered successfully",
                response);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(
                true,
                "Login successful",
                response);

        return ResponseEntity.ok(apiResponse);
    }
    
}
