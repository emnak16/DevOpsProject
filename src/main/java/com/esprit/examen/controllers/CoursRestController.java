package com.esprit.examen.controllers;

import java.util.List;

import com.esprit.examen.dto.CoursModel;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.services.ICoursService;

@RestController
public class CoursRestController {
@Autowired
ICoursService coursService;

@PostMapping("/ajouterCours")
@ResponseBody
public Cours ajouterCours(@RequestBody CoursModel coursModel) {
	Cours cours = new Cours(coursModel);
	coursService.addCours(cours);
	return cours;
}

@PutMapping("/modifierCours")
@ResponseBody
public Cours modifierCours(@RequestBody CoursModel coursModel) {
	Cours cours = new Cours(coursModel);
	coursService.modifierCours(cours);
	return cours;
}

@DeleteMapping("/supprimerCours/{coursId}")
@ResponseBody
public void supprimerCours(@PathVariable("coursId") Long coursId) {
	coursService.supprimerCours(coursId);
}

@GetMapping("/listeCours")
@ResponseBody
public List<Cours> listeCours() {
	
	return  coursService.getCours();
}

	@GetMapping("/getCoursById/{coursId}")
	@ResponseBody
	public  ResponseEntity<Cours> getCoursByID(@PathVariable("coursId") Long coursId) {

		try {
			Cours cours =  coursService.findcoursById(coursId);
			return new ResponseEntity<>(cours, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


@PutMapping("/affecterCoursASession/{coursId}/{sessionId}")
@ResponseBody
public String affecterFormateurASession(@PathVariable("coursId")  Long coursId, @PathVariable("sessionId") Long sessionId) {
	coursService.affecterCoursASession(coursId, sessionId);
	return "cours affecté correctement";
}


	@GetMapping("/coursGenerate")
	public void generatePDF(HttpServletResponse response) throws IOException, DocumentException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		coursService.export(response);
	}

}
