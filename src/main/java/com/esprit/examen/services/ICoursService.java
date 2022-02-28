package com.esprit.examen.services;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Session;
import com.esprit.examen.exception.NotFoundException;
import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ICoursService {
	Long addCours(Cours cours);

	Long modifierCours(Cours cours);

	void supprimerCours(Long coursId);

	List<Cours> getCours();

	List<Session> retrieveHistory(Long coursId) throws Exception, NotFoundException;

	Cours findcoursById(Long coursId) throws Exception;

	void affecterCoursASession(Long coursId, Long sessionId) throws Exception;

	 void export(HttpServletResponse response) throws IOException, DocumentException;



}
