package com.esprit.examen.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
 @Getter
@Setter
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
    @OneToMany(mappedBy = "formateur",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private Set<Session> sessions;

    @Autowired
    @Transient
    PasswordEncoder passwordEncoder;

       public Formateur(Long id, String nom, String prenom, Poste poste,  Contrat contrat, String email, String password) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.contrat = contrat;
        this.email = email;
        this.password = password;
    }


    public Formateur(String prenom, String nom, Poste poste, Contrat contrat, String email, String password) {
        this.nom=nom;
        this.prenom= prenom;
        this.poste = poste;
        this.contrat = contrat;
        this.email = email;
        this.password = password;

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
                ", password='" + passwordEncoder.encode(password) + '\'' +
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
        // TODO Auto-generated constructor stub
    }


}
