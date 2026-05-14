package com.quizapp.service;

import com.quizapp.dto.auth.AuthResponse;
import com.quizapp.dto.auth.LoginRequest;
import com.quizapp.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}