package com.example.spring_security_demo.ra.security.userPrincipal;

import com.example.spring_security_demo.ra.model.Users;
import com.example.spring_security_demo.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username).get();
        if (user == null){
            throw new  UsernameNotFoundException("user not found");
        } else {
            return CustomUserDetail.build(user);
        }
    }
}
