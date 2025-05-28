package com.paccy.springbootne2025.services.impl;

import com.paccy.springbootne2025.entities.User;
import com.paccy.springbootne2025.exceptions.BadRequestException;
import com.paccy.springbootne2025.exceptions.UnauthorizedException;
import com.paccy.springbootne2025.repository.UserRepository;
import com.paccy.springbootne2025.request.InitiateAccountVerificationRequest;
import com.paccy.springbootne2025.request.InitiatePasswordResetRequest;
import com.paccy.springbootne2025.request.LoginRequest;
import com.paccy.springbootne2025.response.AuthResponse;
import com.paccy.springbootne2025.security.JwtService;
import com.paccy.springbootne2025.security.UserDetailServiceImpl;
import com.paccy.springbootne2025.services.IAuthService;
import com.paccy.springbootne2025.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Optional<User> _user= userRepository.findByEmail(loginRequest.email());
        if (_user.isEmpty()){
            throw new UnauthorizedException("Invalid Email of Password");
        }
        User user=_user.get();
        if (!passwordEncoder.matches(loginRequest.password(),user.getPassword())){
            throw new UnauthorizedException("Invalid Email or Password");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(user.getEmail());
        Authentication authToken= new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

//        Claims
        Map<String,Object> claims = new HashMap<>();
        claims.put("email",user.getEmail());
        String token= jwtService.generateToken(claims,userDetails);

        return new AuthResponse(token,user);

    }
//
//
//    @Override
//    public void initiateAccountVerification(InitiateAccountVerificationRequest verificationRequest) {
//        User user= userRepository.findByEmail(verificationRequest.getEmail()).orElseThrow(
//                () -> new UnauthorizedException("User doesn't exist")
//        );
//        if (user.getUserStatus() == EUserStatus.VERIFIED){
//            throw new BadRequestException("User is already verified");
//
//        }
//        String verificationCode= Utility.generateAuthCode();
//        LocalDateTime verificationCodeExpiresAt= LocalDateTime.now().plusHours(6);
//        user.setActivationCode(verificationCode);
//        user.setActivationCodeExpiresAt(verificationCodeExpiresAt);
//        this.mailService.sendActivateAccountEmail(user.getEmail(),user.getFullName(),verificationCode);
//        userRepository.save(user);
//
//    }
//
//    @Override
//    public void verifyAccount(String verificationCode) {
////     Check if the user exists
//        Optional<User> _user= this.userRepository.findByActivationCode(verificationCode);
//        if (_user.isEmpty()){
//            throw new ResourceNotFoundException("User",verificationCode,verificationCode);
//        }
//        User  user=_user.get();
//        System.out.println("User"+user);
//
////        Check if the activationCode is not expired
//        if (user.getActivationCodeExpiresAt().isBefore(LocalDateTime.now())){
//            throw new BadRequestException("Verification has code expired,try generating another");
//        }
//
//        if (!Utility.isCodeValid(user.getActivationCode(),verificationCode)){
//            throw new BadRequestException("Verification code is invalid");
//        }
//        user.setUserStatus(EUserStatus.VERIFIED);
//        user.setActivationCode(null);
//        user.setActivationCodeExpiresAt(null);
//        this.mailService.sendAccountVerifiedSuccessfullyEmail(user.getEmail(),user.getFullName());
//        this.userRepository.save(user);
//
//    }
//
//    @Override
//    public void initiatePasswordReset(InitiatePasswordResetRequest passwordResetRequest) {
//        User user = this.userRepository.findByEmail(passwordResetRequest.getEmail()).orElseThrow(
//                () -> new UnauthorizedException("User doesn't exist")
//        );
//        user.setActivationCode(Utility.randomUUID(3, 0, 'N'));
//        user.setUserStatus(EUserStatus.RESET);
//        this.userRepository.save(user);
//        mailService.sendResetPasswordMail(user.getEmail(), user.getFullName(), user.getActivationCode());
//    }
//
//
//    @Override
//    public void resetPassword(String email,String verificationCode,String newPassword) {
//        User user= this.userRepository.findByEmail(email).orElseThrow(
//                () -> new UnauthorizedException("User doesn't exist")
//        );
//        if (Utility.isCodeValid(user.getActivationCode(),verificationCode)){
//            user.setPassword(passwordEncoder.encode(newPassword));
//            user.setActivationCode(Utility.randomUUID(6, 0, 'N'));
//            user.setUserStatus(EUserStatus.VERIFIED);
//            this.userRepository.save(user);
//            this.mailService.sendPasswordResetSuccessEmail(user.getEmail(),user.getFullName());
//        }
//        else {
//            throw new BadRequestException("Verification code is invalid");
//        }
//    }
}
