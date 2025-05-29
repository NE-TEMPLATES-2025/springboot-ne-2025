package com.paccy.springbootne2025.mappers;

import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.enums.ERole;
import com.paccy.springbootne2025.enums.EmployeeStatus;
import com.paccy.springbootne2025.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
private final PasswordEncoder passwordEncoder;


    public Employee toEmployee(RegisterUserRequest registerUserRequest) {


        return Employee
                .builder()
                .firstName(registerUserRequest.firstName())
                .lastName(registerUserRequest.lastName())
                .email(registerUserRequest.email())
                .password(passwordEncoder.encode(registerUserRequest.password()))
                .mobile(registerUserRequest.phoneNumber())
                .employeeStatus(EmployeeStatus.ACTIVE)
                .dob(registerUserRequest.dob())
                .roles(ERole.EMPLOYEE)
                .build();


    }

    public Employee toAdmin(RegisterUserRequest registerUserRequest) {


        return Employee
                .builder()
                .firstName(registerUserRequest.firstName())
                .lastName(registerUserRequest.lastName())
                .email(registerUserRequest.email())
                .password(passwordEncoder.encode(registerUserRequest.password()))
                .mobile(registerUserRequest.phoneNumber())
                .employeeStatus(EmployeeStatus.ACTIVE)
                .dob(registerUserRequest.dob())
                .roles(ERole.ADMIN)
                .build();


    }

    public Employee toManager(RegisterUserRequest registerUserRequest) {


        return Employee
                .builder()
                .firstName(registerUserRequest.firstName())
                .lastName(registerUserRequest.lastName())
                .email(registerUserRequest.email())
                .password(passwordEncoder.encode(registerUserRequest.password()))
                .mobile(registerUserRequest.phoneNumber())
                .employeeStatus(EmployeeStatus.ACTIVE)
                .dob(registerUserRequest.dob())
                .roles(ERole.MANAGER)
                .build();


    }

}
