package com.paccy.springbootne2025.services;

import com.paccy.springbootne2025.entities.Deduction;
import com.paccy.springbootne2025.request.CreateDeductionRequest;
import org.springframework.data.domain.Page;

public interface IDeductionService {

    Deduction createDeduction(CreateDeductionRequest createDeductionRequest);
    Page<Deduction> getAllDeductions(int page, int size);
};
