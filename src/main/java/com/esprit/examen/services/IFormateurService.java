package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.Session;
import com.esprit.examen.entities.TypeCours;

public interface IFormateurService {
	Long addorEditFormateur(Formateur formateur);

	//Long modifierFormateur(Formateur formateur);

	void supprimerFormateur(Long formateurId);
	
	Long nombreFormateursImpliquesDansUnCours(TypeCours typeCours);
		
	List<Formateur> listFormateurs();

	Formateur findByIdFormateur(Long formateurId);
}
