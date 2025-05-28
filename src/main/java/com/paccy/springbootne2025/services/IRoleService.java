package com.paccy.springbootne2025.services;

import com.paccy.springbootne2025.entities.Role;
import com.paccy.springbootne2025.request.CreateRoleRequest;
import java.util.List;
import java.util.UUID;

public interface IRoleService {

    public List<Role> getRoles();
    public Role getRoleById(UUID roleID);
    public Role createRole(CreateRoleRequest createRoleRequest);
}
