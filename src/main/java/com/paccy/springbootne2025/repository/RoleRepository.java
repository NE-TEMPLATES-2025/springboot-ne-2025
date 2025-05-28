package com.paccy.springbootne2025.repository;

import com.paccy.springbootne2025.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
