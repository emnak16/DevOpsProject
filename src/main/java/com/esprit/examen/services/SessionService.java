package com.esprit.examen.services;

import com.esprit.examen.entities.Formateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Session;
import com.esprit.examen.repositories.SessionRepository;

import java.util.List;

@Service
public class SessionService implements ISessionService{

	@Autowired
	SessionRepository sessionRepository;
	@Override
	public Long addSession(Session session) {
		sessionRepository.save(session);
		return session.getId();
	}


	@Override
	public Long modifierSession(Session session) {
		sessionRepository.save(session);
		return session.getId();
	}

	@Override
	public void supprimerSession(Long sessionId) {
		sessionRepository.deleteById(sessionId);
	}

	@Override
	public void affecterFormateurASession(Long formateurId, Long sessionId) {
			/*todo*/
		Session s = sessionRepository.findById(sessionId).get();

		
	}
	@Override
	public List<Session> listSession() {

		return sessionRepository.findAll();
	}

}
