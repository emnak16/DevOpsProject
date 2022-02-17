package com.esprit.examen.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Cours implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@Enumerated(EnumType.STRING)
	private TypeCours typeCours;
	private String intitule;
	@ManyToMany(mappedBy="cours",fetch = FetchType.EAGER)
	private Set<Session> sessions;
	private Long prix;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public TypeCours getTypeCours() {
		return typeCours;
	}
	public void setTypeCours(TypeCours typeCours) {
		this.typeCours = typeCours;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	
	public Set<Session> getSessions() {
		return sessions;
	}
	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}
	@Override
	public String toString() {
		return "Cours [id=" + id + ", description=" + description + ", typeCours=" + typeCours + ", intitule="
				+ intitule + "]";
	}
	public Cours(Long id, String description, TypeCours typeCours, String intitule) {
		super();
		this.id = id;
		this.description = description;
		this.typeCours = typeCours;
		this.intitule = intitule;
	}

	public Cours(String description, TypeCours typeCours, String intitule, Set<Session> sessions, Long prix) {
		this.description = description;
		this.typeCours = typeCours;
		this.intitule = intitule;
		this.sessions = sessions;
		this.prix = prix;
	}

	public Cours() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
