package com.paccy.springbootne2025.repository;

import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.entities.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {
    Optional<Employment> findByEmployee(Optional<Employee> employee);
}
