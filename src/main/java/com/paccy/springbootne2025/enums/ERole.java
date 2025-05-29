package com.paccy.springbootne2025.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public enum ERole {
    ADMIN(
            Set.of(
                    EPermission.ADMIN_CREATE,
                    EPermission.ADMIN_READ
            )
    ),
    MANAGER(
            Set.of(
                    EPermission.MANAGER_READ,
                    EPermission.MANAGER_CREATE
            )
    ),
    EMPLOYEE(
            Set.of(
                    EPermission.EMPLOYEE_READ,
                    EPermission.EMPLOYEE_CREATE
            )
    );

    @Getter
    private final Set<EPermission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
          return  authorities;

    }
}
