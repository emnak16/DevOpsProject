package com.esprit.examen.services;

import com.esprit.examen.config.RegexTests;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.repositories.FormateurRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class FormateurService implements IFormateurService {

    @Autowired
    FormateurRepository formateurRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Long addorEditFormateur(Formateur formateur) {
        if (formateur.getEmail() != null && Boolean.FALSE.equals(RegexTests.isValidMail(formateur.getEmail()))) {
            log.severe("email wrong format");
            return -1l;
        } else if (formateur.getNom() == null || Boolean.FALSE.equals(RegexTests.isValidName(formateur.getNom()))) {
            log.severe("Name wrong format" + formateur.getNom());

            return -1l;
        } else if (formateur.getPassword() == null || Boolean.FALSE.equals(RegexTests.isValidPassword(formateur.getPassword()))) {
            log.severe("Password wrong format");
            return -1l;
        } else if (formateur.getPhone() != null && Boolean.FALSE.equals(RegexTests.isAvalidPhone(formateur.getPhone()))) {
            log.severe("Phone number  wrong format");
            return -1l;
        } else {
            formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));
            formateurRepository.save(formateur);
            log.info("added Trainer with information " + formateur.toString());
            return 1l;
        }
    }


    @Override
    public void supprimerFormateur(Long formateurId) {
        formateurRepository.deleteById(formateurId);
        log.info("deleted trainer with id " + formateurId);

    }

    @Override
    public Long nombreFormateursImpliquesDansUnCours(TypeCours typeCours) {
        return formateurRepository.nombreFormateursImpliquesDansUnCours(typeCours.name());

    }


    @Override
    public Formateur findFormateurById(Long formateurId) {
        Formateur f = formateurRepository.findById(formateurId).orElseGet(Formateur::new);
        log.info("extracted trainer with information: " + f.toString());
        return f;

    }


    @Override
    public List<Formateur> listFormateurs() {

        return formateurRepository.findAll(Sort.by("nom"));
    }

    @Override
    public List<Formateur> findFormateurByName(String name) {
        return formateurRepository.findAll()
                .stream()
                .filter(f -> name.equals(f.getNom()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Formateur> findFormateurByLastName(String prenom) {
        return formateurRepository.findAll()
                .stream()
                .filter(f -> prenom.equals(f.getPrenom()))
                .collect(Collectors.toList());

    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
