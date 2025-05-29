package com.paccy.springbootne2025.controllers;


import com.paccy.springbootne2025.request.LoginRequest;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.response.AuthResponse;
import com.paccy.springbootne2025.services.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse response= authService.login(loginRequest);
        return  new ApiResponse<>("User logged in successfully", HttpStatus.OK,response).toResponseEntity();
    }


}
