package com.paccy.springbootne2025.services;

import com.paccy.springbootne2025.entities.Employment;
import com.paccy.springbootne2025.request.CreateEmploymentRequest;
import org.springframework.data.domain.Page;

public interface IEmploymentService {

    Employment registerEmployment(CreateEmploymentRequest createEmploymentRequest);
    Page<Employment> getAllEmployments(int page,int size);
}
