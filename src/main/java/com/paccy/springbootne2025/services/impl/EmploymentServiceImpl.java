package com.paccy.springbootne2025.services.impl;

import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.entities.Employment;
import com.paccy.springbootne2025.exceptions.UserNotFoundException;
import com.paccy.springbootne2025.repository.EmployeeRepository;
import com.paccy.springbootne2025.repository.EmploymentRepository;
import com.paccy.springbootne2025.request.CreateEmploymentRequest;
import com.paccy.springbootne2025.services.IEmploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmploymentServiceImpl implements IEmploymentService {

    private final EmploymentRepository employmentRepository;
    private  final EmployeeRepository employeeRepository;

    @Override
    public Employment registerEmployment(CreateEmploymentRequest createEmploymentRequest) {
        Optional<Employee> _employee= employeeRepository.findByCode(createEmploymentRequest.employeeId());
        if (_employee.isEmpty()){
         throw new UserNotFoundException("Employee not found");
        }

// create a new employment

        Employment newEmployment= Employment
                .builder()
                .employmentStatus(createEmploymentRequest.employmentStatus())
                .baseSalary(createEmploymentRequest.baseSalary())
                .employee(_employee.get())
                .department(createEmploymentRequest.department())
                .position(createEmploymentRequest.position())
                .joinDate(createEmploymentRequest.joinDate())
                .build();

        return employmentRepository.save(newEmployment);
    }


    @Override
    public Page<Employment> getAllEmployments(int page, int size) {
        Pageable pageable= PageRequest.of(page, size);
        return employmentRepository.findAll(pageable);
    }
}
