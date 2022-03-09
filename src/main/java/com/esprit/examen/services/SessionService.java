package com.esprit.examen.services;


import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.Session;
import com.esprit.examen.exception.BadDataException;
import com.esprit.examen.exception.NotFoundException;
import com.esprit.examen.repositories.FormateurRepository;
import com.esprit.examen.repositories.SessionRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownServiceException;
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
	public void affecterFormateurASession(Long formateurId, Long sessionId) throws BadDataException, UnknownServiceException {

		Session s = sessionRepository.findById(sessionId)
				.orElseThrow(UnknownServiceException::new);
		Formateur f = formateurRepository.findById(formateurId).orElseThrow(UnknownServiceException::new);

		if(f.getSessions() == null){
			Set<Session> sessions = new HashSet<>();
			sessions.add(s);
			f.setSessions(sessions);
		}else{
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
	public Session findByIdSession( Long sessionId) throws NotFoundException {
		return sessionRepository.findById(sessionId).orElseThrow(NotFoundException::new);
	}

	@Override
	public Set<Session> findSessionByFormateur(Long formateurId) {


		return sessionRepository.findSessionByFormateur(formateurId);
	}

	@Override
	public void budgerSession(Long sessionId, Long salary) {
		try {
			Session s = findByIdSession(sessionId);
		double totalCoursPrice = s.getCours().stream().mapToDouble(Cours::getPrix).sum();
		s.setPrice((totalCoursPrice*s.getDuree())+salary);
		modifierSession(s);
		}catch(Exception e){
			log.severe(e.getMessage());
			log.severe("couldnt find session with id : " + sessionId);

		}
	}

	

}
