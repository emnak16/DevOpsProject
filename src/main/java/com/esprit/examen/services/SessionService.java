package com.esprit.examen.services;

import com.esprit.examen.entities.Contrat;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.repositories.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.entities.Session;
import com.esprit.examen.repositories.SessionRepository;

import java.util.List;

@Service
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
		Formateur f = formateurRepository.findById(formateurId).get();
		s.setFormateur(f);
		f.getSessions().add(s);
		modifierSession(s);
		formateurService.modifierFormateur(f);
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
		return listSession().stream().filter(session -> session.getFormateur().getId()==formateurId).findFirst().get();
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
