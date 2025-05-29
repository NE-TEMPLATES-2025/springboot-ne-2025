package com.paccy.springbootne2025.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.paccy.springbootne2025.enums.EmploymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_code",nullable = false)
    @JsonBackReference
    private Employee employee;

    private  String department;
    private String position;
    private Double baseSalary;
    private EmploymentStatus employmentStatus;
    private LocalDate joinDate;
}
