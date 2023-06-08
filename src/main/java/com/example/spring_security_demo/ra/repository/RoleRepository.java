package com.example.spring_security_demo.ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring_security_demo.ra.model.RoleName;
import com.example.spring_security_demo.ra.model.Roles;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRoleName(RoleName roleName);
}
