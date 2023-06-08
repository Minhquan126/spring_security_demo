package com.example.spring_security_demo.ra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring_security_demo.ra.model.Users;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
