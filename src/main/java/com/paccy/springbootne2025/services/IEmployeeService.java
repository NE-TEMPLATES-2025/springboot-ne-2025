package com.paccy.springbootne2025.services;

import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.request.RegisterUserRequest;

import java.util.Optional;

public interface IEmployeeService {

    Employee createEmployee(RegisterUserRequest registerUserRequest);
    Employee createAdmin(RegisterUserRequest registerUserRequest);
    Employee createManager(RegisterUserRequest registerUserRequest);

    Employee getMe();
    Optional<Employee> getEmployeeByEmail(String email);
}
