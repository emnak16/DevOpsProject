package com.esprit.examen.services;



import com.esprit.examen.entities.Session;
import com.esprit.examen.exception.BadDataException;
import com.esprit.examen.exception.NotFoundException;

import java.net.UnknownServiceException;
import java.util.List;
import java.util.Set;

public interface ISessionService {
	Long addSession(Session session);

	Long modifierSession(Session session);

	void supprimerSession(Long sessionId);

	void affecterFormateurASession(Long formateurId, Long sessionId) throws BadDataException, UnknownServiceException;

	List<Session> listSession();

	Session findByIdSession(Long sessionId) throws NotFoundException;

	Set<Session> findSessionByFormateur(Long formateurId) throws NotFoundException;

	void budgerSession(Long sessionId, Long salary);




}
