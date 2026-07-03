package com.flowbrain.backend.auth.service;

import com.flowbrain.backend.auth.dto.LoginRequest;
import com.flowbrain.backend.auth.dto.LoginResponse;
import com.flowbrain.backend.auth.dto.RegisterRequest;
import com.flowbrain.backend.auth.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}
