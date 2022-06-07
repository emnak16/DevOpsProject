package com.esprit.examen.controllers;

import com.esprit.examen.dto.FormateurModel;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.exception.BadDataException;
import com.esprit.examen.exception.LogInException;
import com.esprit.examen.exception.NotFoundException;
import com.esprit.examen.services.IFormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/formateurs")
public class FormateurRestController {

    @Autowired
    IFormateurService formateurService;

    @PostMapping("/addOrEditFormateur")
    @ResponseBody
    public ResponseEntity<String> ajouterFormateur(@RequestBody FormateurModel formateurModel) {
        try {
            Formateur formateur = new Formateur(formateurModel);
            formateurService.addorEditFormateur(new Formateur(formateurModel));
            return new ResponseEntity<>(formateur.toString(), HttpStatus.OK);
        } catch (BadDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

/*
    @GetMapping("/logIn")
    @ResponseBody
    public ResponseEntity<Integer> login(@RequestBody String email, String password) {
        int res = 1;
        try {
            formateurService.logIn(email, password);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (LogInException e) {
            res = -1;
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

        }
    }*/
    @DeleteMapping("/supprimerFormateur/{formateurId}")
    @ResponseBody
    public ResponseEntity<String> supprimerFormateur(@PathVariable("formateurId") Long formateurId) {
        formateurService.supprimerFormateur(formateurId);
        return ResponseEntity.ok("Formateur deleted");
    }

    @GetMapping("/nombreFormateursImpliquesDansUnCours/{typeCours}")
    @ResponseBody
    public ResponseEntity<Long> nombreFormateursImpliquesDansUnCours(@PathVariable("typeCours") TypeCours typeCours) {
        Long nb = formateurService.nombreFormateursImpliquesDansUnCours(typeCours);
        return new ResponseEntity<>(nb, HttpStatus.OK);

    }

    @GetMapping("/getFormateurById/{formateruId}")
    @ResponseBody
    public ResponseEntity<Formateur> getFormateurByID(@PathVariable("formateruId") Long formateurId) {
        try {
            Formateur formateur = formateurService.findFormateurById(formateurId);
            return new ResponseEntity<>(formateur, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listFormateur")
    @ResponseBody
    public ResponseEntity<Collection<Formateur>> listFormateurs() {

        return new ResponseEntity<>(formateurService.listFormateurs(), HttpStatus.OK);

    }


}
