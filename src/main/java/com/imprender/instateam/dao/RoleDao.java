package com.imprender.instateam.dao;

import com.imprender.instateam.model.Role;
import com.imprender.instateam.model.StandardModel;

import java.util.List;

public interface RoleDao {

    List<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void delete(Role role);
}
