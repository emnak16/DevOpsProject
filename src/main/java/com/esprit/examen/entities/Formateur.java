package com.esprit.examen.entities;

import com.esprit.examen.dto.FormateurModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Getter
@Setter
@Entity
public class Formateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    private Poste poste;
    @Enumerated(EnumType.STRING)
    private Contrat contrat;
    private String phone;
    private String email;
    private String password;
    private Boolean admin;
    @OneToMany(mappedBy = "formateur", fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Set<Session> sessions;

    public Formateur(FormateurModel formateurModel) {
    }


    @Override
    public String toString() {
        return "Formateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", poste=" + poste +
                ", contrat=" + contrat +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }

    public Formateur(String nom, String prenom, Poste poste, Contrat contrat,String phone, String email, String password) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.contrat = contrat;
        this.phone=phone;
        this.email = email;
        this.password = password;
    }


    public Formateur() {
        super();
    }


}
