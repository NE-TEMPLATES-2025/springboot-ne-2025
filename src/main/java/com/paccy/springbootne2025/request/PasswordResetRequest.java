package com.paccy.springbootne2025.request;


import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {

    @Email
    private  String email;
}
