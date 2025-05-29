package com.paccy.springbootne2025.request;


import com.paccy.springbootne2025.enums.ERole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record RegisterUserRequest(

        @NotBlank
        @NotEmpty
        String firstName,

        @NotBlank
        @NotEmpty
        String lastName,
        @NotBlank
        @NotEmpty
        @Email
        String email,

        @NotBlank
        @NotEmpty
        String phoneNumber,
        @NotBlank
        @NotEmpty
        @Pattern(regexp = "[A-Za-z0-9]{8,60}")
        String password,
        LocalDate dob

) {

}
