package com.paccy.springbootne2025.controllers;


import com.paccy.springbootne2025.entities.Deduction;
import com.paccy.springbootne2025.request.CreateDeductionRequest;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.services.impl.DeductionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deductions")
@RequiredArgsConstructor
public class DeductionController {
    private final DeductionServiceImpl deductionService;


    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Deduction>> createDeduction(
                    @RequestBody @Valid CreateDeductionRequest createDeductionRequest
            ){
        Deduction response = deductionService.createDeduction(createDeductionRequest);
        return new ApiResponse<>("Deduction created successfully", HttpStatus.CREATED,response).toResponseEntity();
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<Deduction>>> getAllDeductions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Deduction> response = deductionService.getAllDeductions(page,size);
        return new ApiResponse<>("Deductions retrieved  successfully", HttpStatus.CREATED,response).toResponseEntity();
    }
}
