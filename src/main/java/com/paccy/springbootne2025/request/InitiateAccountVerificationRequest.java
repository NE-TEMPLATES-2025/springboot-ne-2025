package com.paccy.springbootne2025.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiateAccountVerificationRequest {

    @Email
    @NotBlank
    String email;
}
