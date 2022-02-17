package com.esprit.examen.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.esprit.examen.config.RegexTests;
import com.esprit.examen.entities.Session;
import com.esprit.examen.repositories.SessionRepository;
import lombok.extern.java.Log;
import com.esprit.examen.entities.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.examen.repositories.CoursRepository;

@Service
@Log
public class CoursService implements ICoursService {

	@Autowired
	CoursRepository coursRepository;
	@Autowired
	SessionRepository sessionRepository;

	@Override
	public Long addCours(Cours cours) {
		coursRepository.save(cours);
		log.info("added course "+ cours.toString());
		return cours.getId();
	}

	@Override
	public Long modifierCours(Cours cours) {
		coursRepository.save(cours);
		log.info("modified course "+ cours.toString());
		return cours.getId();
	}





	@Override
	public void supprimerCours(Long coursId) {
		coursRepository.deleteById(coursId);
		log.info("deleted course with id "+ coursId);

	}

	@Override
	public List<Cours> getCours() {

		List<Cours> cours =   coursRepository.findAll();
		log.info("retreived courses"+ cours);
		return cours;
	}

    @Override
	public Cours findcoursById(Long coursId)
	{Cours c = coursRepository.findById(coursId).get();
		log.info("extracted course with information: "+ c.toString());
		return c;}



	@Override
	public void affecterCoursASession(Long coursId, Long sessionId)
	{   Cours c = coursRepository.findById(coursId).get();
		Session s = sessionRepository.findById(sessionId).get();
		Set<Session> set = new HashSet<>();
		set.add(s);
		c.setSessions(set);
		log.info("added sessions"+ c.toString());
		coursRepository.save(c);



	}



}
