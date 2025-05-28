package com.paccy.springbootne2025.request;


import java.util.List;
import java.util.UUID;

public record RegisterUserRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Long nationalId,
        String password,
        List<UUID> roleIds
) {

}
