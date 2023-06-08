package com.example.spring_security_demo.ra.service;

import com.example.spring_security_demo.ra.model.Users;

import java.util.Optional;

public interface IUserService extends IGenericService<Users, Long>{
    Optional<Users> findByUserName(String userName);
    boolean existByUserName(String userName);
    boolean existByEmail(String email);
}
