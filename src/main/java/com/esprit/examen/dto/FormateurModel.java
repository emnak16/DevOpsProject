package com.esprit.examen.dto;

import com.esprit.examen.entities.Contrat;
import com.esprit.examen.entities.Poste;
import com.esprit.examen.entities.Session;
import lombok.Data;

import java.util.Set;

@Data
public class FormateurModel {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nom;
    private String prenom;

    private Poste poste;

    private Contrat contrat;
    private String phone;
    private String email;
    private String password;
    private Boolean admin;

    private Set<Session> sessions;




    public FormateurModel() {
        super();
    }


    public FormateurModel(FormateurModel formateurModel) {
    }
}
