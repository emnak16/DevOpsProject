package com.esprit.examen.services;

import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.Session;

import java.util.List;

public interface ISessionService {
	Long addSession(Session session);

	Long modifierSession(Session session);

	void supprimerSession(Long sessionId);
	
	void affecterFormateurASession (Long formateurId, Long sessionId);

	List<Session> listSession();


}
