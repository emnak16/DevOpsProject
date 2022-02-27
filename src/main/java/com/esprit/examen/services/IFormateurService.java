package com.esprit.examen.services;

import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.exception.BadDataException;
import com.esprit.examen.exception.LogInException;
import com.esprit.examen.exception.NotFoundException;

import java.util.List;

public interface IFormateurService {
	Long addorEditFormateur(Formateur formateur) throws BadDataException;


	void supprimerFormateur(Long formateurId);

	Long nombreFormateursImpliquesDansUnCours(TypeCours typeCours);

	Formateur findFormateurById(Long formateurId) throws NotFoundException;

	List<Formateur> listFormateurs();

	List<Formateur> findFormateurByName(String name);

	List<Formateur> findFormateurByLastName(String prenom);


    int logIn(String email, String password) throws LogInException;

    Formateur findFormateurByEmail(String email) throws NotFoundException;
}
