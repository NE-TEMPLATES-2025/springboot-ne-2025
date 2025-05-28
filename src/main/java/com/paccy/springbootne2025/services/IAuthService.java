package com.paccy.springbootne2025.services;


import com.paccy.springbootne2025.request.InitiateAccountVerificationRequest;
import com.paccy.springbootne2025.request.InitiatePasswordResetRequest;
import com.paccy.springbootne2025.request.LoginRequest;
import com.paccy.springbootne2025.response.AuthResponse;


public interface IAuthService {

    AuthResponse login(LoginRequest loginRequest);

//    void  initiateAccountVerification(InitiateAccountVerificationRequest verificationRequest);
//    void verifyAccount(String verificationCode);
//    void initiatePasswordReset(InitiatePasswordResetRequest passwordResetRequest);
//    void resetPassword(String email,String verificationCode,String newPassword);
}
