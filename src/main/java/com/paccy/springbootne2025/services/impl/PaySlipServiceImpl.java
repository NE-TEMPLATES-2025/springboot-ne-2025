package com.paccy.springbootne2025.services.impl;

import com.paccy.springbootne2025.entities.Deduction;
import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.entities.Employment;
import com.paccy.springbootne2025.entities.PaySlip;
import com.paccy.springbootne2025.enums.PaySlipStatus;
import com.paccy.springbootne2025.repository.DeductionRepository;
import com.paccy.springbootne2025.repository.EmployeeRepository;
import com.paccy.springbootne2025.repository.EmploymentRepository;
import com.paccy.springbootne2025.repository.PaySlipRepository;
import com.paccy.springbootne2025.request.CreatePaySlipRequest;
import com.paccy.springbootne2025.services.IPaySlipService;
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
public class PaySlipServiceImpl implements IPaySlipService {

    private final PaySlipRepository paySlipRepository;
    private  final EmploymentRepository employmentRepository;
    private final DeductionRepository deductionRepository;
    private final EmployeeRepository employeeRepository;
    @Override

    public PaySlip createPaySlip(CreatePaySlipRequest createPaySlipRequest) {
        // Get employee by ID
        Optional<Employee> employeeOpt = employeeRepository.findByCode(createPaySlipRequest.employeeId());
        if (employeeOpt.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }

        Employee employee = employeeOpt.get();

        // Get employment
        Optional<Employment> employmentOpt = employmentRepository.findByEmployee(Optional.of(employee));
        if (employmentOpt.isEmpty()) {
            throw new RuntimeException("Employment not found for employee");
        }
        Employment employment = employmentOpt.get();
        Double baseSalary = employment.getBaseSalary();
        if (baseSalary == null) {
            throw new RuntimeException("Base salary is null");
        }

        // Fetch deductions safely
        Deduction employeeTaxDeduction = deductionRepository.findByName("EmployeeTax")
                .orElseThrow(() -> new RuntimeException("EmployeeTax deduction not found"));
        Deduction transportDeduction = deductionRepository.findByName("Transport")
                .orElseThrow(() -> new RuntimeException("Transport deduction not found"));
        Deduction pensionDeduction = deductionRepository.findByName("Pension")
                .orElseThrow(() -> new RuntimeException("Pension deduction not found"));
        Deduction medicalInsuranceDeduction = deductionRepository.findByName("MedicalInsurance")
                .orElseThrow(() -> new RuntimeException("MedicalInsurance deduction not found"));
        Deduction housingDeduction = deductionRepository.findByName("Housing")
                .orElseThrow(() -> new RuntimeException("Housing deduction not found"));
        Deduction otherDeduction = deductionRepository.findByName("Others")
                .orElseThrow(() -> new RuntimeException("Others deduction not found"));

        double employeeTaxCost = ((double) employeeTaxDeduction.getPercentage() / 100) * baseSalary;
        double transportCost = ((double) transportDeduction.getPercentage() / 100) * baseSalary;
        double pensionCost = ((double) pensionDeduction.getPercentage() / 100) * baseSalary;
        double medicalInsuranceCost = ((double) medicalInsuranceDeduction.getPercentage() / 100) * baseSalary;
        double housingCost = ((double) housingDeduction.getPercentage() / 100) * baseSalary;
        double othersCost = ((double) otherDeduction.getPercentage() / 100) * baseSalary;

        //  gross and net salary
        double totalDeductions = employeeTaxCost + transportCost + pensionCost
                + medicalInsuranceCost + housingCost + othersCost;
        double grossSalary = baseSalary + totalDeductions;
        double netSalary = grossSalary - totalDeductions;

        // Build and save the payslip
        PaySlip paySlip = PaySlip.builder()
                .employee(employee)
                .transportAmount(transportCost)
                .pensionAmount(pensionCost)
                .medicalInsuranceAmount(medicalInsuranceCost)
                .otherTaxedAmount(othersCost)
                .netSalary(netSalary)
                .grossSalary(grossSalary)
                .paySlipStatus(PaySlipStatus.PENDING)
                .month(createPaySlipRequest.month())
                .year(createPaySlipRequest.year())
                .houseAmount(housingCost)
                .employeeTaxedAmount(employeeTaxCost)
                .build();

        return paySlipRepository.save(paySlip);
    }


    @Override
    public Page<PaySlip> getPaySlips(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paySlipRepository.findAll(pageable);
    }

    @Override
    public Page<PaySlip> getPaySlipsInTimeRange(int page, int size, int month, Long year) {
        Pageable pageable = PageRequest.of(page, size);
        return  paySlipRepository.findByYearAndMonth(year, month, pageable);
    }

    @Override
    public Page<PaySlip> approvePaySlips(int page, int size, int month, Long year) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PaySlip> paySlipsPage = paySlipRepository.findByYearAndMonth(year, month, pageable);

        // Update each PaySlip to PAID
        paySlipsPage.getContent().forEach(paySlip -> {
            paySlip.setPaySlipStatus(PaySlipStatus.PAID);
        });

        // Save all updated payslips
        paySlipRepository.saveAll(paySlipsPage.getContent());

        return paySlipsPage;
    }

}
