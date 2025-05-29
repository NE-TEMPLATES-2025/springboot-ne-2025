package com.paccy.springbootne2025.controllers;


import com.paccy.springbootne2025.entities.PaySlip;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.services.impl.PaySlipServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pay-slip")
@RequiredArgsConstructor
public class PaySlipController {

    private final PaySlipServiceImpl paySlipService;

    public ResponseEntity<ApiResponse<PaySlip>>
}
