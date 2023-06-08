package com.example.spring_security_demo.ra.service.serviceIMPL;

import com.example.spring_security_demo.ra.repository.UserRepository;
import com.example.spring_security_demo.ra.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_security_demo.ra.model.Users;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceIMPL implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
         userRepository.deleteById(id);
         return true;
    }

    @Override
    public boolean save(Users users) {
        userRepository.save(users);
        return true;
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Optional<Users> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
