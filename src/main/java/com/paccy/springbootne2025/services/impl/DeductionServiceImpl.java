package com.paccy.springbootne2025.services.impl;

import com.paccy.springbootne2025.entities.Deduction;
import com.paccy.springbootne2025.repository.DeductionRepository;
import com.paccy.springbootne2025.request.CreateDeductionRequest;
import com.paccy.springbootne2025.services.IDeductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeductionServiceImpl implements IDeductionService {

    private  final DeductionRepository deductionRepository;

    @Override
    public Deduction createDeduction(CreateDeductionRequest createDeductionRequest) {
        Deduction deduction = Deduction
                .builder()
                .name(createDeductionRequest.name())
                .percentage(createDeductionRequest.percentage())
                .build();

        return deductionRepository.save(deduction);
    }

    @Override
    public Page<Deduction> getAllDeductions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return deductionRepository.findAll(pageable);
    }
}
