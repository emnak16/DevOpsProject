package com.esprit.examen.controllers;


import com.esprit.examen.dto.SessionModel;
import com.esprit.examen.entities.Session;
import com.esprit.examen.exception.BadDataException;
import com.esprit.examen.services.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class SessionRestController {

	@Autowired
	ISessionService sessionService;
	
	@PostMapping("/ajouterSession")
	@ResponseBody
	public Session ajouterSession(@RequestBody SessionModel sessionModel) {
		Session session = new Session(sessionModel);
		sessionService.addSession(session);
		return session;
	}

	@PutMapping("/modifierSession")
	@ResponseBody
	public Session modifierSession(@RequestBody SessionModel sessionModel) {
		Session session = new Session(sessionModel);
		sessionService.modifierSession(session);
		return session;
	}
	
	@PutMapping("/affecterFormateurASession/{formateurId}/{sessionId}")
	@ResponseBody
	public String affecterFormateurASession(@PathVariable("formateurId") Long formateurId, @PathVariable("sessionId") Long sessionId) throws BadDataException {
		sessionService.affecterFormateurASession(formateurId, sessionId);
		return "formateur affecté correctement";
	}

	@DeleteMapping("/supprimerSession/{sessionId}")
	@ResponseBody
	public void supprimerSession(@PathVariable("sessionId") Long sessionId) {
		sessionService.supprimerSession(sessionId);
	}


}
