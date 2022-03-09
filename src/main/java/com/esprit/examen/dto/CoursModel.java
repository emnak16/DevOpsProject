package com.esprit.examen.dto;

import com.esprit.examen.entities.Session;
import com.esprit.examen.entities.TypeCours;
import lombok.*;


import java.util.Set;



@Data
public class CoursModel {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;

    private TypeCours typeCours;
    private String intitule;

    private Set<Session> sessions;

    private double prix;




    public CoursModel() {
        super();
    }


}
