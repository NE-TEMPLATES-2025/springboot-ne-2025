package com.paccy.springbootne2025.response;


import com.paccy.springbootne2025.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private Employee employee;


}
