package com.imprender.instateam.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity //not all clases are necessarily entities. They could be value objects that we want just to embed!
public class StandardModel {

    //Data validation in Spring --> 1) JPA validation annotations
    //2) capture validation in what is called a binding result in the controller
    // 3) if there are validation errors, back to the form and show the errors
    //Validation annotations: @NotNull, @Size(min=3, max= 12), @Pattern(regex = "#[0-9a-fA-F]{6}")
    //Then we go to the controller....


    //Example
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // tells h2 it should insert IDs as unique to the table (not the whole db)
    private Long id;

    @Transient
    private String someTransientVariable;

    //All columns are not transient now, except colletions! Unless we want it to be transient, we have to specify relation
//    @OneToMany
//    private List<Object> whatEverItIs;

    //If we have a large object, we need to mark it with Large Object Annotation (SLOB, BLOB, LOB)
    //If in a relationship oneToMany, we want to avoid to create a new table for the relationship, we need to include a mappedBy property in the annotation


    //We need a default constructor for our ORM to work!

    public StandardModel() {
    }

}
