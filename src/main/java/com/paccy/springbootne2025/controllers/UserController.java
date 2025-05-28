package com.paccy.springbootne2025.controllers;



import com.paccy.springbootne2025.entities.User;
import com.paccy.springbootne2025.request.RegisterUserRequest;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody RegisterUserRequest registerUserRequest) {
        User response = userService.createUser(registerUserRequest);
        return new ApiResponse<>("User Registered Successfully", HttpStatus.CREATED,response).toResponseEntity();
    }
}
