package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.repositories.FormateurRepository;

@Service
public class FormateurService implements IFormateurService{

	@Autowired
	FormateurRepository formateurRepository;
	@Override
	public Long addorEditFormateur(Formateur formateur) {
		formateurRepository.save(formateur);
		return formateur.getId();
	}

/*	@Override
	public Long modifierFormateur(Formateur formateur) {
		formateurRepository.save(formateur);
		return formateur.getId();
	}*/

	@Override
	public void supprimerFormateur(Long formateurId) {
		formateurRepository.deleteById(formateurId);
		
	}

	@Override
	public Long nombreFormateursImpliquesDansUnCours(TypeCours typeCours) {
		return formateurRepository.nombreFormateursImpliquesDansUnCours(typeCours.name());
		
	}



	@Override
	public List<Formateur> listFormateurs() {
		
		return formateurRepository.findAll();
	}

	@Override
	public Formateur findByIdFormateur(Long formateurId) {
		return formateurRepository.findById(formateurId).get();
	}
	
	

}
