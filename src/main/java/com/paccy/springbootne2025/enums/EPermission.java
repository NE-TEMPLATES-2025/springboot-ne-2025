package com.paccy.springbootne2025.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EPermission {



        ADMIN_READ("admin:read"),
        ADMIN_CREATE("admin:create"),
        MANAGER_READ("manager:read"),
        MANAGER_CREATE("manager:create"),
        EMPLOYEE_READ("employee:read"),
        EMPLOYEE_CREATE("employee:create");

        @Getter
        private final String permission;

}
