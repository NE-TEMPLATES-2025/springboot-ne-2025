package com.paccy.springbootne2025.request;


import com.paccy.springbootne2025.enums.ERole;

public record CreateRoleRequest(
        ERole name
) {
}
