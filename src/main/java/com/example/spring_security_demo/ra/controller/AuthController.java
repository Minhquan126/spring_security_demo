package com.example.spring_security_demo.ra.controller;

import com.example.spring_security_demo.ra.dto.request.FormLogin;
import com.example.spring_security_demo.ra.dto.request.FormRegister;
import com.example.spring_security_demo.ra.dto.response.JwtUserResponse;
import com.example.spring_security_demo.ra.dto.response.ResponseMessage;
import com.example.spring_security_demo.ra.model.RoleName;
import com.example.spring_security_demo.ra.model.Roles;
import com.example.spring_security_demo.ra.model.Users;
import com.example.spring_security_demo.ra.security.jwt.JwtTokenProvider;
import com.example.spring_security_demo.ra.security.userPrincipal.CustomUserDetail;
import com.example.spring_security_demo.ra.service.serviceIMPL.RoleServiceIMPL;
import com.example.spring_security_demo.ra.service.serviceIMPL.UserServiceIMPL;
import lombok.Builder;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@Builder
@RequestMapping("/api/v4/auth")
public class AuthController {
    @Autowired
   private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserServiceIMPL userServiceIMPL;
    @Autowired
   private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleServiceIMPL roleServiceIMPL;
    @PostMapping("/singin")
    public ResponseEntity doSingIn(@RequestBody FormLogin formLogin){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(formLogin.getUserName(),formLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        String token = jwtTokenProvider.createToken(customUserDetail);
        List<String> roles = customUserDetail.getAuthorities().stream().map(
                role -> role.getAuthority()
        ).collect(Collectors.toList());
        JwtUserResponse jwtUserResponse = new JwtUserResponse(customUserDetail.getUsername(),
                customUserDetail.getEmail(),
                token,
                roles);
        return ResponseEntity.ok(jwtUserResponse);
    }
@PostMapping("/singup")
    public ResponseEntity register(@RequestBody FormRegister formRegister){
        if (userServiceIMPL.existByUserName(formRegister.getUserName())){
            return ResponseEntity.ok(new ResponseMessage("userName is existed"));
        }
        if (userServiceIMPL.existByEmail(formRegister.getEmail())){
            return ResponseEntity.ok(new ResponseMessage("Email is existed"));
        }
    Set<String> roles = formRegister.getRoles();
        Set<Roles> listRoles = new HashSet<>();
        if (roles == null || roles.isEmpty()){
            listRoles.add(roleServiceIMPL.findByRoleName(RoleName.USER));
        } else {
            roles.forEach(
                    role -> {
                        switch (role){
                            case "ADMIN":
                                listRoles.add(roleServiceIMPL.findByRoleName(RoleName.ADMIN));
                            case "PM":
                                listRoles.add(roleServiceIMPL.findByRoleName(RoleName.PM));
                            case "USER":
                                listRoles.add(roleServiceIMPL.findByRoleName(RoleName.USER));
                        }
                    }
            );
        }
    Users user = new Users(formRegister.getUserName(),
            formRegister.getEmail(),
            passwordEncoder.encode(formRegister.getPassword()),
            listRoles);
        userServiceIMPL.save(user);
        return ResponseEntity.ok(new ResponseMessage("register is success"));
}
}
