package com.paccy.springbootne2025.controllers;


import com.paccy.springbootne2025.entities.Role;
import com.paccy.springbootne2025.request.CreateRoleRequest;
import com.paccy.springbootne2025.response.ApiResponse;
import com.paccy.springbootne2025.services.impl.RoleServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceImpl roleService;

    @PostMapping()
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody CreateRoleRequest createRoleRequest) {
        Role response= roleService.createRole(createRoleRequest);
        return new ApiResponse<>("Role Created Successfully", HttpStatus.CREATED,response).toResponseEntity();
    }

    @GetMapping()
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<Role>>> getRoles() {
        List<Role> response= roleService.getRoles();
        return new ApiResponse<>("Role List Successfully", HttpStatus.OK,response).toResponseEntity();
    }
}
