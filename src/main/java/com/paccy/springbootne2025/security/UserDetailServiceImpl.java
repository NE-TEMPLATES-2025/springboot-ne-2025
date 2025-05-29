package com.paccy.springbootne2025.security;


import com.paccy.springbootne2025.entities.Employee;
import com.paccy.springbootne2025.exceptions.UserNotFoundException;
import com.paccy.springbootne2025.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl  implements UserDetailsService {

    private final EmployeeRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee employee=  userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("Employee with that email is not found"));
       List<SimpleGrantedAuthority> authorities= employee.getRoles().getAuthorities();
        return  new org.springframework.security.core.userdetails.User(
                employee.getEmail(),
                employee.getPassword(),
                authorities);
    }
}

