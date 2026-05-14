package com.quizapp.controller;

import com.quizapp.dto.auth.AuthResponse;
import com.quizapp.dto.auth.LoginRequest;
import com.quizapp.dto.auth.RegisterRequest;
import com.quizapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication APIs")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public AuthResponse register(
            @Valid @RequestBody
            RegisterRequest request) {

        return authService.register(request);
    }

    @Operation(summary = "Login user and generate JWT token")
    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody
            LoginRequest request) {

        return authService.login(request);
    }
}