package com.esprit.examen.services;

import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;

import java.util.List;

public interface IFormateurService {
	Long addorEditFormateur(Formateur formateur);


	void supprimerFormateur(Long formateurId);

	Long nombreFormateursImpliquesDansUnCours(TypeCours typeCours);

	Formateur findFormateurById(Long formateurId);

	List<Formateur> listFormateurs();

	List<Formateur> findFormateurByName(String name);

	List<Formateur> findFormateurByLastName(String prenom);
}
