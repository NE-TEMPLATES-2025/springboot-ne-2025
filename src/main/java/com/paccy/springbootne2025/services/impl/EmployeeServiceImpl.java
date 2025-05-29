package com.paccy.springbootne2025.services.impl;


import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.exceptions.BadRequestException;
import com.paccy.springbootne2025.mappers.EmployeeMapper;
import com.paccy.springbootne2025.repository.EmployeeRepository;
import com.paccy.springbootne2025.request.RegisterUserRequest;
import com.paccy.springbootne2025.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {


    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee createEmployee(RegisterUserRequest registerUserRequest) {


            Optional<Employee> existingEmployee= employeeRepository.findByEmail(registerUserRequest.email());

            if (existingEmployee.isPresent()){
                throw new BadRequestException("User with the same email already exists");
            }
            Employee employee = employeeMapper.toEmployee(registerUserRequest);
            return employeeRepository.save(employee);
    }

    @Override
    public Employee createAdmin(RegisterUserRequest registerUserRequest) {

        Optional<Employee> existingEmployee= employeeRepository.findByEmail(registerUserRequest.email());

        if (existingEmployee.isPresent()){
            throw new BadRequestException("User with the same email already exists");
        }
        Employee employee = employeeMapper.toAdmin(registerUserRequest);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee createManager(RegisterUserRequest registerUserRequest) {

        Optional<Employee> existingEmployee= employeeRepository.findByEmail(registerUserRequest.email());

        if (existingEmployee.isPresent()){
            throw new BadRequestException("User with the same email already exists");
        }
        Employee employee = employeeMapper.toManager(registerUserRequest);
        return employeeRepository.save(employee);
    }


    @Override
    public Employee getMe() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof  String)){
            UserDetails userDetails= (UserDetails) authentication.getPrincipal();

            Optional<Employee> user= getEmployeeByEmail(userDetails.getUsername());
            return user.orElse(null);
        }
        else {
            log.warn("No authenticated user found");
        }
        return null;
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
