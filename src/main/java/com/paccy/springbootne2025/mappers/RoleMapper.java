package com.paccy.springbootne2025.mappers;



import com.paccy.springbootne2025.entities.Role;
import com.paccy.springbootne2025.request.CreateRoleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public Role toRole(CreateRoleRequest createRoleRequest) {
        return Role.
                builder()
                .name(createRoleRequest.name())
                .build();


    }

}
