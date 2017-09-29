package com.imprender.instateam.dao;

import com.imprender.instateam.model.Project;

import java.util.List;

public interface ProjectDao {

    List<Project> findAll();

    Project findById(Long id);

    void save(Project project);

    void delete(Project project);
}
