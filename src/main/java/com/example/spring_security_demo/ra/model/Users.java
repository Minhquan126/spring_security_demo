package com.example.spring_security_demo.ra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user",uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName"),
        @UniqueConstraint(columnNames = "email")
})
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userName",unique = true,nullable = false)
    @Size(min = 6)
    private String userName;
    @Column(name = "email",unique = true,nullable = false)
    @Email
    @Size(min = 6)
    private String email;
    @Column(name = "password",unique = true,nullable = false)
    @Size(min = 6)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserRole",
    joinColumns = @JoinColumn(name = "userId"),
    inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Roles> roles = new HashSet<>();

    public Users(String userName, String email, String password, Set<Roles> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
