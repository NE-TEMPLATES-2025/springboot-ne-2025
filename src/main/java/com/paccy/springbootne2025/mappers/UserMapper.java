package com.paccy.springbootne2025.mappers;

import com.paccy.springbootne2025.entities.User;
import com.paccy.springbootne2025.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
private final PasswordEncoder passwordEncoder;


    public User toUser(RegisterUserRequest registerUserRequest) {


        return User
                .builder()
                .firstName(registerUserRequest.firstName())
                .lastName(registerUserRequest.lastName())
                .email(registerUserRequest.email())
                .password(passwordEncoder.encode(registerUserRequest.password()))
                .phoneNumber(registerUserRequest.phoneNumber())
                .nationalId(registerUserRequest.nationalId())
                .build();


    }

}
