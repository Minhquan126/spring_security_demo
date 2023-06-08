package com.example.spring_security_demo.ra.service;

import com.example.spring_security_demo.ra.model.RoleName;
import com.example.spring_security_demo.ra.model.Roles;

public interface IRoleService extends IGenericService<Roles,Long>{
    Roles findByRoleName(RoleName roleName);
}
