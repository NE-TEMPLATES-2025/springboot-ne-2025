package com.paccy.springbootne2025.repository;


import com.paccy.springbootne2025.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByCode(Long code);
}
