package com.paccy.springbootne2025.controllers;


import com.paccy.springbootne2025.entities.Employment;
import com.paccy.springbootne2025.request.CreateEmploymentRequest;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.services.impl.EmployeeServiceImpl;
import com.paccy.springbootne2025.services.impl.EmploymentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employment")
@RequiredArgsConstructor
public class EmploymentController {

    private final EmploymentServiceImpl employmentService;


    @PostMapping("/new")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Employment>> createEmployment(
            @RequestBody @Valid CreateEmploymentRequest createEmploymentRequest
    ){
        Employment response= employmentService.registerEmployment(createEmploymentRequest);
        return new ApiResponse<>("Employment registered successfully", HttpStatus.CREATED,response).toResponseEntity();

    }

    @GetMapping ("/all")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Page<Employment>>> getAllEmployments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Employment> response= employmentService.getAllEmployments(page,size);
        return new ApiResponse<>("Employments retrieved successfully", HttpStatus.OK,response).toResponseEntity();

    }
}
