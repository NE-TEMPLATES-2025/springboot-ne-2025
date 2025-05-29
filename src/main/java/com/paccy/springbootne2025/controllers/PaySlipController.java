package com.paccy.springbootne2025.controllers;


import com.paccy.springbootne2025.entities.PaySlip;
import com.paccy.springbootne2025.request.CreatePaySlipRequest;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.services.impl.PaySlipServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pay-slip")
@RequiredArgsConstructor
public class PaySlipController {

    private final PaySlipServiceImpl paySlipService;

    @PostMapping("/new")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<PaySlip>> createPaySlip(
            @RequestBody @Valid CreatePaySlipRequest createPaySlipRequest
            ) {

        PaySlip response = paySlipService.createPaySlip(createPaySlipRequest);
        return new ApiResponse<>("Pay slip created successfully", HttpStatus.CREATED,response).toResponseEntity();
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<ApiResponse<Page<PaySlip>>> getAllPaySlips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<PaySlip> response = paySlipService.getPaySlips(page,size);
        return new ApiResponse<>("Pay slips retrieved successfully", HttpStatus.OK,response).toResponseEntity();
    }


    @GetMapping("/in")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<ApiResponse<Page<PaySlip>>> getAllPaySlipsInMonth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "5") int month,
            @RequestParam(defaultValue = "2025") long year
    ){
        Page<PaySlip> response= paySlipService.getPaySlipsInTimeRange(page,size,month,year);
        return new ApiResponse<>("Pay slips in"+month + " "+ year + " retrieved successfully", HttpStatus.OK,response).toResponseEntity();
    }


    @PutMapping("/approve/in")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<ApiResponse<Page<PaySlip>>> approvePaySlipInMonth(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "5") int month,
            @RequestParam(defaultValue = "2025") long year
    ){
        Page<PaySlip> response= paySlipService.approvePaySlips(page,size,month,year);
        return new ApiResponse<>("Pay slips in"+month + " "+ year + " have been successfully paid", HttpStatus.CREATED,response).toResponseEntity();
    }
}
