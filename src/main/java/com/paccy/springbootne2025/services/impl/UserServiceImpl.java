package com.paccy.springbootne2025.services.impl;


import com.paccy.springbootne2025.entities.Role;
import com.paccy.springbootne2025.entities.User;
import com.paccy.springbootne2025.exceptions.BadRequestException;
import com.paccy.springbootne2025.mappers.UserMapper;
import com.paccy.springbootne2025.repository.RoleRepository;
import com.paccy.springbootne2025.repository.UserRepository;
import com.paccy.springbootne2025.request.RegisterUserRequest;
import com.paccy.springbootne2025.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements IUserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(RegisterUserRequest registerUserRequest) {

            List<Role> roles = roleRepository.findAllById(registerUserRequest.roleIds());

            Optional<User> existingUser= userRepository.findByEmail(registerUserRequest.email());

            if (existingUser.isPresent()){
                throw new BadRequestException("User with the same email already exists");
            }
            User user = userMapper.toUser(registerUserRequest);
            user.setRoles(roles);
            return userRepository.save(user);
    }
}
