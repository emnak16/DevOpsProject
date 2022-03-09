package com.esprit.examen.dto;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Formateur;
import lombok.Data;




import java.util.Date;
import java.util.Set;



@Data
public class SessionModel {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date dateDebut;

    private Date dateFin;
    private Long duree;
    private String description;

    Formateur formateur;

    Set<Cours> cours;
    private Double price;

    private Long salaireF;


    public SessionModel() {
        super();
    }


}
