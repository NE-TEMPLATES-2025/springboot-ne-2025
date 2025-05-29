package com.paccy.springbootne2025.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.paccy.springbootne2025.enums.PaySlipStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "pay_slips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaySlip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_code",nullable = false)
    @JsonBackReference
    private Employee employee;

    private Double houseAmount;
    private Double transportAmount;
    private Double  employeeTaxedAmount ;
    private Double pensionAmount;
    private Double medicalInsuranceAmount;
    private Double otherTaxedAmount;
    private Double grossSalary;
    private Double netSalary;
    private int month;
    private Long year;
    private PaySlipStatus paySlipStatus;

}
