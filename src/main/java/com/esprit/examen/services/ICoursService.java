package com.esprit.examen.services;

import java.io.IOException;
import java.util.List;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Session;
import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;

public interface ICoursService {
	Long addCours(Cours cours);

	Long modifierCours(Cours cours);

	void supprimerCours(Long coursId);

	List<Cours> getCours();

	List<Session> retrieveHistory(Long coursId) throws Exception;

	Cours findcoursById(Long coursId) throws Exception;

	void affecterCoursASession(Long coursId, Long sessionId);

	 void export(HttpServletResponse response) throws IOException, DocumentException;



}
