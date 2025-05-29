package com.paccy.springbootne2025.request;

import com.paccy.springbootne2025.enums.EmploymentStatus;

import java.time.LocalDate;

public record CreateEmploymentRequest(
        Long employeeId,
        String department,
        String position,
        Double baseSalary,
        EmploymentStatus employmentStatus,
        LocalDate joinDate
) {
}
