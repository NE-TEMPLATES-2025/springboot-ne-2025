package com.paccy.springbootne2025.repository;

import com.paccy.springbootne2025.entities.PaySlip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaySlipRepository extends JpaRepository<PaySlip, UUID> {

    Page<PaySlip> findByYearAndMonth(Long year, int month, Pageable pageable);

}
