package com.example.spring_security_demo.ra.service.serviceIMPL;

import com.example.spring_security_demo.ra.repository.RoleRepository;
import com.example.spring_security_demo.ra.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring_security_demo.ra.model.RoleName;
import com.example.spring_security_demo.ra.model.Roles;

import java.util.List;
@Service
public class RoleServiceIMPL implements IRoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<Roles> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean save(Roles roles) {
        roleRepository.save(roles);
        return true;
    }

    @Override
    public Roles findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Roles findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).get();
    }
}
