package com.imprender.instateam.dao;

import com.imprender.instateam.model.StandardModel;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//So Springs pick this class as an autowirable implementation --> it is the only class implementing the interface so it is autowirable
@Repository
public class StandardDaoImpl implements StandarDao{

    //We need a session factory --> we autowire it here
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<StandardModel> findAll() {
        Session session = sessionFactory.openSession();
        List<StandardModel> standards = session.createCriteria(StandardModel.class).list();


        session.close();
        return standards;
    }

    @Override
    public StandardModel findById(Long id) {
        //Todo: finish ths
        return null;
    }

    @Override
    public void save(StandardModel standardModel) {
        // Open session
        Session session = sessionFactory.openSession();

        //BEGIN TRANSACTION
        session.beginTransaction();

        //save object
        session.save(standardModel);

        //commmit transaction --> so we can roll back if something happens
        session.getTransaction().commit();

        //close the session
        session.close();

        //Todo: is there a way to "gotoservice"??
    }

    @Override
    public void delete(StandardModel standardModel) {
        //Todo: finish ths

    }
}
