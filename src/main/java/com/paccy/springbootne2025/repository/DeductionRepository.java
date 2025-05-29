package com.paccy.springbootne2025.repository;

import com.paccy.springbootne2025.entities.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DeductionRepository extends JpaRepository<Deduction,Long> {

    Optional<Deduction> findByName(String name);
}
