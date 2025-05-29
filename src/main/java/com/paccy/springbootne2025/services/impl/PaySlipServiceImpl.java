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
//        Find the deductions by name;
        Deduction employeeTaxDeduction = deductionRepository.findByName("EmployeeTax");
        Deduction transportDeduction= deductionRepository.findByName("Transport");
        Deduction pensionDeduction=deductionRepository.findByName("Pension");
        Deduction medicalInsuranceDeduction=deductionRepository.findByName("MedicalInsurance");
        Deduction housingDeduction=deductionRepository.findByName("Housing");
        Deduction otherDeduction=deductionRepository.findByName("Others");

//        Getting the employment
        Optional<Employee> employee= employeeRepository.findByCode(createPaySlipRequest.employeeId());
        if(employee.isPresent()){
            Optional<Employment> existingEmployment= employmentRepository.findByEmployee(employee);
            if (existingEmployment.isEmpty()){
                throw  new RuntimeException("employment not found");
            }
            Double employmentBaseSalary=existingEmployment.get().getBaseSalary();
//        //Get the cost of each deduction

            Double employeeTaxCost= (employeeTaxDeduction.getPercentage() /100) * employmentBaseSalary;
            Double transportCost= (transportDeduction.getPercentage() /100) * employmentBaseSalary;
            Double pensionCost= (pensionDeduction.getPercentage() /100) * employmentBaseSalary;
            Double medicalInsuranceCost= (medicalInsuranceDeduction.getPercentage() /100) * employmentBaseSalary;
            Double housingCost= (housingDeduction.getPercentage() / 100) * employmentBaseSalary;
            Double othersCost= (otherDeduction.getPercentage() / 100) * employmentBaseSalary;

//            Calculate the Gross salary
            Double grossSalary= employmentBaseSalary + (employeeTaxCost + transportCost + pensionCost+ medicalInsuranceCost + housingCost + othersCost);
//            Calculate the net Salary
            Double taxedMoney= employeeTaxCost + transportCost + pensionCost+ medicalInsuranceCost + housingCost + othersCost;
            Double netSalary= grossSalary - taxedMoney;

//            Go ahead and create the payslip

            PaySlip paySlip = PaySlip
                    .builder()
                    .employee(employee.get())
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


            return  paySlipRepository.save(paySlip);

        }
        else {
            throw  new RuntimeException("employee not found");
        }


    }

    @Override
    public Page<PaySlip> getPaySlips(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return paySlipRepository.findAll(pageable);
    }

    @Override
    public Page<PaySlip> getPaySlipsInTimeRange(int page, int size, int month, Long year) {
        return null;
    }
}
