package com.paccy.springbootne2025.config;


import com.paccy.springbootne2025.security.JwtAuthFilter;
import com.paccy.springbootne2025.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailServiceImpl userDetailService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(
                AbstractHttpConfigurer::disable
        ).authorizeHttpRequests(
                req -> req.requestMatchers(
                               "/api/v1/deductions/**",
                                "/api/v1/user/admin/create",
                                "/api/v1/user/manager/create",
                                "/api/v1/user/me",
                                "/api/v1/employment/**",
                                "/api/v1/pay-slip/**")
                                .authenticated()
                                .anyRequest()
                                .permitAll()
        )
                .userDetailsService(userDetailService)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return  http.build();
    }

}
