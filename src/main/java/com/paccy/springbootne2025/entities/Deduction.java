package com.paccy.springbootne2025.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deductions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "deduction_name")
    private String name;

    private int percentage;

}
