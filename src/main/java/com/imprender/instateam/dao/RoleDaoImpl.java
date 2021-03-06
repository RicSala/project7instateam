package com.imprender.instateam.dao;

import com.imprender.instateam.model.Collaborator;
import com.imprender.instateam.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAll() {

        Session session = sessionFactory.openSession();

        List<Role> roles = session.createCriteria(Role.class).list();

        session.close();

        return roles;
    }

    @Override
    public Role findById(Long id) {

        Session session = sessionFactory.openSession();

        Role role = session.get(Role.class, id);

        session.close();

        return role;
    }

    @Override
    public void save(Role role) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(role);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void delete(Role role) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(role);

        session.getTransaction().commit();

        session.close();
    }
}
