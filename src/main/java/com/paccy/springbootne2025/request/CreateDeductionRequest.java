package com.paccy.springbootne2025.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateDeductionRequest(
        @NotBlank
        @NotEmpty
        String name,

        @Min(1)
        int percentage
) {
}
