package com.paccy.springbootne2025.security;


import com.paccy.springbootne2025.entities.User;
import com.paccy.springbootne2025.exceptions.UserNotFoundException;
import com.paccy.springbootne2025.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl  implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=  userRepository.findByEmail(email).orElseThrow(()->
                new UserNotFoundException("User with that email is not found"));
       List<GrantedAuthority> authorities= user.getUserRoles();
        return  new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}

