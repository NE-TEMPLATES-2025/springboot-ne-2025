package com.paccy.springbootne2025.services;



import com.paccy.springbootne2025.request.LoginRequest;
import com.paccy.springbootne2025.response.AuthResponse;


public interface IAuthService {

    AuthResponse login(LoginRequest loginRequest);


}
