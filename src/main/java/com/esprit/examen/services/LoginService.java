package com.esprit.examen.services;

import com.esprit.examen.entities.Formateur;
import com.esprit.examen.exception.LogInException;
import com.esprit.examen.exception.NotFoundException;
import com.esprit.examen.repositories.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    @Autowired
    FormateurService formateurService;

    @Autowired
    PasswordEncoder passwordEncoder;


    public int logIn(String email, String password) throws LogInException {
        try {
            Formateur f = formateurService.findFormateurByEmail(email);
            if (passwordEncoder.matches(password, f.getPassword()))
                return 1;
            else
                return -1;
        } catch (NotFoundException e) {
            throw new LogInException("No user found with these credentials");

        }

    }

    }
