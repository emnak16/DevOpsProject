package com.esprit.examen;

import com.esprit.examen.entities.*;
import com.esprit.examen.services.ICoursService;
import com.esprit.examen.services.IFormateurService;
import com.esprit.examen.services.ISessionService;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.*;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class SessionTest {

    @Autowired
    ISessionService sessionService;
    @Autowired
    IFormateurService formateurService;
    @Autowired
    ICoursService coursService;

    @Test
    public void ajouterSessionTest(){

        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);
        boolean added = sessionService.listSession().stream().anyMatch(session -> session.toString().equals(s.toString()));
        assertTrue(added);
    }

    @Test
    public void modifierSessionTest(){
        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);
        s.setDescription("something");
        sessionService.modifierSession(s);
        boolean added = sessionService.listSession().stream().anyMatch(session -> session.toString().equals(s.toString()));
        assertTrue(added);

    }

    @Test
    public void supprimerSessionTest() throws ParseException{
        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);
        sessionService.supprimerSession(s.getId());
        boolean added = sessionService.listSession().stream().anyMatch(session -> session.toString().equals(s.toString()));
        assertFalse(added);


    }

    @Test
    public void listSessionTest() {
        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);
        List<Session> sessionList= sessionService.listSession();
        assertNotEquals(sessionList.size(), 0);
        sessionService.supprimerSession(s.getId());

    }
    @Test
    public void affecterFormateurASessionTest(){
        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI, "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        Session s1 = sessionService.findByIdSession(s.getId());
        Formateur f1 = formateurService.findByIdFormateur(f.getId());
        sessionService.affecterFormateurASession(s1.getId(), f1.getId());
        Session s2 = sessionService.findSessionByFormateur(f.getId());
        assertNotNull(s2);
        sessionService.supprimerSession(s1.getId());
        formateurService.supprimerFormateur(f1.getId());
        log.info(""+s.getId());
    }

    @Test
    public void budgerSessionTest(){
        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Set<Session> setS = new HashSet<Session>();
        Set<Cours> setC = new HashSet<Cours>();
        Cours c = new Cours("first course", TypeCours.Informatique, "cours", setS, 10L);
        coursService.addCours(c);
        setC.add(c);
        Session s = new Session(date1, date2, 3L, "First session, month long",setC);
        sessionService.addSession(s);
        s.setSalaireF(500L);
        sessionService.modifierSession(s);
        setS.add(s);

        sessionService.budgerSession(s.getId(), s.getSalaireF());

        if(s.getPrice()!=null){
        assertEquals(s.getPrice(), Optional.of((530L)));
            sessionService.supprimerSession(s.getId());
            coursService.supprimerCours(c.getId());
        }
    }
}
