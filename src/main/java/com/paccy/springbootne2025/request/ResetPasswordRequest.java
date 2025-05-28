package com.paccy.springbootne2025.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String newPassword;
    private String verificationCode;

}
