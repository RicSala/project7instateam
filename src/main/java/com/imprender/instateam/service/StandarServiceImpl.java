package com.imprender.instateam.service;

import com.imprender.instateam.dao.StandarDao;
import com.imprender.instateam.model.StandardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//With this annotations, Spring know that this is a bean (a concrete type of bean) that can be autowired and inserted elsewhere (namely...our controllers)
@Service
public class StandarServiceImpl implements StandarService {


    //Todo: What is the difference in Spring for the repo, component, service annotations?

    //Since we need to call the DAO layer, we autowire it!
    //We are autowiring an INTERFACE, because we code for interface so things remained loosy coupled and we can change it easily
    //Spring will find the only implementation of this interface!
    @Autowired
    StandarDao dao;

    @Override
    public List<StandardModel> findAll() {
        return dao.findAll();
    }

    @Override
    public StandardModel findById(Long id) {
        return null;
    }

    @Override
    public void save(StandardModel standardModel) {
        dao.save(standardModel);
    }

    @Override
    public void delete(StandardModel standardModel) {

    }
}
