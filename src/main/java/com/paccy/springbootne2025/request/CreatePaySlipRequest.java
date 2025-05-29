package com.paccy.springbootne2025.request;

public record CreatePaySlipRequest(
        Long employeeId,
        int month,
        Long year
) {
}


