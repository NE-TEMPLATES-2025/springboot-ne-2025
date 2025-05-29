package com.paccy.springbootne2025.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.paccy.springbootne2025.enums.ERole;
import com.paccy.springbootne2025.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;


    @Column(nullable = false,name = "first_name")
    private String firstName;

    @Column(nullable = false,name = "last_name")
    private String lastName;

    @Column(nullable = false,name = "email",unique = true)
    private String email;

    @Column(nullable = false,name = "password")
    private String password;

    private ERole roles;

    @Column(nullable = false,name = "mobile")
    private String mobile;

    @Column(unique = true)
    private LocalDate dob;
    private EmployeeStatus employeeStatus;


    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employment> employments;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PaySlip> paySlips;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" +roles.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




}
