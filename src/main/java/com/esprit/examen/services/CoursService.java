package com.esprit.examen.services;

import java.io.IOException;
import java.util.*;

import com.esprit.examen.exception.NotFoundException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.esprit.examen.entities.Session;
import com.esprit.examen.repositories.SessionRepository;
import lombok.extern.java.Log;
import com.esprit.examen.entities.Cours;
import org.springframework.beans.factory.annotation.Autowired;


import com.esprit.examen.repositories.CoursRepository;



@Service
@Log
public class CoursService implements ICoursService {

	@Autowired
	CoursRepository coursRepository;

	@Autowired
	ISessionService sessionService;

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
	public Cours findcoursById(Long coursId) throws NotFoundException {
		Cours c = coursRepository.findById(coursId).orElseThrow(NotFoundException::new);
		log.info("extracted course with information: "+ c.toString());
		return c;}



	@Override
	public void affecterCoursASession(Long coursId, Long sessionId)
	{
		try {
			Cours c = coursRepository.findById(coursId).orElseThrow(Exception::new);
			Session s = sessionService.findByIdSession(sessionId);
			Set<Session> set = new HashSet<>();
			set.add(s);
			c.setSessions(set);
			coursRepository.save(c);
			log.info("added sessions" + c.toString());
		}catch(Exception e){
			e.getMessage();
		}


	}

	public void export(HttpServletResponse response) throws IOException, DocumentException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("This is a title.", fontTitle);


		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);

		Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);


		document.add(paragraph);
		document.add(paragraph2);
		document.close();
	}


	public List<Session> retrieveHistory(Long coursId) throws NotFoundException {
		Cours c = coursRepository.findById(coursId).orElseThrow(NotFoundException::new);

		return (List<Session>) c.getSessions();

	}


}
