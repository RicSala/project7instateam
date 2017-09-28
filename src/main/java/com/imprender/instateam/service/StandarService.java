package com.imprender.instateam.service;

import com.imprender.instateam.model.StandardModel;

import java.util.List;

public interface StandarService {

    //This layer is supposed to do something else to our data before persisting it or handle it to other layers


    //--> at least it implements the same methods than the dao, because we do not talk directly to the dao!

    List<StandardModel> findAll();

    StandardModel findById(Long id);

    void save(StandardModel standardModel);

    void delete(StandardModel standardModel);
}
