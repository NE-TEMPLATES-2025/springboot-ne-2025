package com.paccy.springbootne2025.services;

import com.paccy.springbootne2025.entities.User;
import com.paccy.springbootne2025.request.RegisterUserRequest;

public interface IUserService {

    User createUser(RegisterUserRequest registerUserRequest);
}
