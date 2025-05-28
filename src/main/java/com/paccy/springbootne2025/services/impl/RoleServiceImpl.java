package com.paccy.springbootne2025.services.impl;


import com.paccy.springbootne2025.entities.Role;
import com.paccy.springbootne2025.exceptions.ResourceNotFoundException;
import com.paccy.springbootne2025.mappers.RoleMapper;
import com.paccy.springbootne2025.repository.RoleRepository;
import com.paccy.springbootne2025.request.CreateRoleRequest;
import com.paccy.springbootne2025.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(UUID roleID) {
        return roleRepository.findById(roleID).orElseThrow(
                ()-> new ResourceNotFoundException("Role not found")
        );
    }

    @Override
    public Role createRole(CreateRoleRequest createRoleRequest) {
        Role role= roleMapper.toRole(createRoleRequest);
       return roleRepository.save(role);
    }
}
