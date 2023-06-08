package com.example.spring_security_demo.ra.dto.request;

import com.example.spring_security_demo.ra.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormRegister {
    private String userName;
    private String email;
    private String password;
    private Set<String> roles;
}
