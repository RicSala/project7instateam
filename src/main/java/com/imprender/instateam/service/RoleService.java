package com.imprender.instateam.service;

import com.imprender.instateam.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void delete(Role role);
}
