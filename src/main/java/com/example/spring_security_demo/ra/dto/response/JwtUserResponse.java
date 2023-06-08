package com.example.spring_security_demo.ra.dto.response;

import com.example.spring_security_demo.ra.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserResponse {
    private String userName;
    private String email;
    private String type = "Bearer";
    private String token;
    private List<String> listRoles;

    public JwtUserResponse(String userName, String email, String token, List<String> listRoles) {
        this.userName = userName;
        this.email = email;
        this.token = token;
        this.listRoles = listRoles;
    }
}
