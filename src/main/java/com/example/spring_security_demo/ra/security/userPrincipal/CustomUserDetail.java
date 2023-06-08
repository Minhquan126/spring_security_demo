package com.example.spring_security_demo.ra.security.userPrincipal;

import com.example.spring_security_demo.ra.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {
    private Long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String email;
    Collection<? extends GrantedAuthority> listRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listRoles;
    }
    public static UserDetails build(Users user){
        List<GrantedAuthority> authorityList = user.getRoles().stream().map(
                role -> new SimpleGrantedAuthority(role.getRoleName().name())
        ).collect(Collectors.toList());
        return new CustomUserDetail(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                authorityList);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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
