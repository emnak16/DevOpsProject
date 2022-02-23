package com.esprit.examen.services;


import com.esprit.examen.entities.Formateur;

import com.esprit.examen.repositories.FormateurRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Session;
import com.esprit.examen.repositories.SessionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Log
public class SessionService implements ISessionService{

	@Autowired
	SessionRepository sessionRepository;
	@Autowired
	FormateurRepository formateurRepository;
	@Autowired
	FormateurService formateurService;
	@Override
	public Long addSession(Session session) {
		sessionRepository.save(session);
		log.info("la session à été ajouté avec succes");
		return session.getId();
	}


	@Override
	public Long modifierSession(Session session) {
		sessionRepository.save(session);
		log.info("la session à été modifié avec succes");
		return session.getId();
	}

	@Override
	public void supprimerSession(Long sessionId) {
		sessionRepository.deleteById(sessionId);
		log.info("la session à été supprimé avec succes");
	}

	@Override
	@Transactional
	public void affecterFormateurASession(Long formateurId, Long sessionId) {

		log.info("idFormateur" + formateurId);
		log.info("idSessin" + sessionId);

		Session s = sessionRepository.findById(sessionId)
				.orElse(new Session());
		Formateur f = formateurRepository.findById(formateurId).orElse(new Formateur());
		log.info("formateur" + f.toString());

		if (f.getSessions() == null) {
			Set sessions = new HashSet<Session>();
			sessions.add(s);
			f.setSessions(sessions);
		} else {
			f.getSessions().add(s);
		}
		formateurService.addorEditFormateur(f);
		s.setFormateur(f);
		modifierSession(s);

		log.info("formateur affecté à la session avec succes");
	}


	@Override
	public List<Session> listSession() {

		return sessionRepository.findAll();
	}

	@Override
	public Session findByIdSession( Long sessionId) {
		return sessionRepository.findById(sessionId).get();
	}

	@Override
	public Session findSessionByFormateur(Long formateurId) {
		return listSession().stream().filter(session -> session.getFormateur().getId().equals(formateurId))
				.findFirst()
				.orElseGet(Session::new);
	}

	@Override
	public void budgerSession(Long sessionId, Long salary) {
		Session s = findByIdSession(sessionId);
		Long nbCours = s.getCours().stream().count();
		Long unitPrice = s.getCours().stream().findFirst().get().getPrix();
		s.setPrice((nbCours*unitPrice*s.getDuree())+salary);
		modifierSession(s);
	}

}
