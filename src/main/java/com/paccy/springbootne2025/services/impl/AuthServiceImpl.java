package com.paccy.springbootne2025.services.impl;

import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.exceptions.UnauthorizedException;
import com.paccy.springbootne2025.repository.EmployeeRepository;
import com.paccy.springbootne2025.request.LoginRequest;
import com.paccy.springbootne2025.response.AuthResponse;
import com.paccy.springbootne2025.security.JwtService;
import com.paccy.springbootne2025.security.UserDetailServiceImpl;
import com.paccy.springbootne2025.services.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final JwtService jwtService;
    private final UserDetailServiceImpl userDetailService;
    private final EmployeeRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Optional<Employee> _employee= userRepository.findByEmail(loginRequest.email());
        if (_employee.isEmpty()){
            throw new UnauthorizedException("Invalid Email of Password");
        }
        Employee employee=_employee.get();
        if (!passwordEncoder.matches(loginRequest.password(),employee.getPassword())){
            throw new UnauthorizedException("Invalid Email or Password");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(employee.getEmail());
        Authentication authToken= new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

//        Claims
        Map<String,Object> claims = new HashMap<>();
        claims.put("email",employee.getEmail());
        String token= jwtService.generateToken(claims,userDetails);

        return new AuthResponse(token,employee);

    }


}
