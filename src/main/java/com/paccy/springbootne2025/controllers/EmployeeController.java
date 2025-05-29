package com.paccy.springbootne2025.controllers;



import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.request.RegisterUserRequest;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.services.impl.EmployeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;


    @PostMapping("/employee/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        Employee response = employeeService.createEmployee(registerUserRequest);
        return new ApiResponse<>("Employee Registered Successfully", HttpStatus.CREATED,response).toResponseEntity();
    }

    @PostMapping("/admin/create")
      @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Employee>> createAdmin(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        Employee response = employeeService.createAdmin(registerUserRequest);
        return new ApiResponse<>("Admin Registered Successfully", HttpStatus.CREATED,response).toResponseEntity();
    }

    @PostMapping("/manager/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Employee>> createManager(@RequestBody @Valid RegisterUserRequest registerUserRequest) {
        Employee response = employeeService.createManager(registerUserRequest);
        return new ApiResponse<>("Manager Registered Successfully", HttpStatus.CREATED, response).toResponseEntity();

    }

    @GetMapping("/me")
    public  ResponseEntity<ApiResponse<Employee>> getMe(){
        Employee response= employeeService.getMe();
        return new  ApiResponse<>("User Profile retrieved successfully", HttpStatus.OK,response).toResponseEntity();
    }



}
