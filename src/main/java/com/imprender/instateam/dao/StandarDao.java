package com.imprender.instateam.dao;

import com.imprender.instateam.model.StandardModel;

import java.util.List;

//Whoever is coding the service layer can code to this interface and not worry about the impl details!

public interface StandarDao {

    List<StandardModel> findAll();

    StandardModel findById(Long id);

    void save(StandardModel standardModel);

    void delete(StandardModel standardModel);
}
