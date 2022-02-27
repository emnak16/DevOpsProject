package com.esprit.examen;

import com.esprit.examen.entities.*;
import com.esprit.examen.exception.BadDataException;
import com.esprit.examen.exception.NotFoundException;
import com.esprit.examen.services.ICoursService;
import com.esprit.examen.services.IFormateurService;
import com.esprit.examen.services.ISessionService;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void supprimerSessionTest() {
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
    public void affecterFormateurASessionTest() throws BadDataException, NotFoundException {
        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI, "97189195", "wbesbes@gmail.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);

        long i = f.getId();
        Session sk = sessionService.listSession().stream().filter(sh -> sh.toString().equals(s.toString())).findFirst().get();
        sessionService.affecterFormateurASession(f.getId(), sk.getId());
        Session s2 = sessionService.findSessionByFormateur(f.getId()).stream().filter(sess -> sess.getId() == s.getId()).findFirst().get();
        assertNotNull(s2);
        sessionService.supprimerSession(s.getId());
        formateurService.supprimerFormateur(f.getId());
    }

    @Test
    public void findSessionByFormateurTest() throws BadDataException, NotFoundException {
        Date date1 = null;
        Date date3 = new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 3L, "First session, month long");
        sessionService.addSession(s);

        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI, "97189195", "wbesbes@gmail.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        Formateur f1 = formateurService.findFormateurByEmail(f.getEmail());

        s.setFormateur(f1);
        sessionService.modifierSession(s);
        Session s2 = sessionService.findByIdSession(s.getId());
        log.info(f1.getId() + "ID");
        log.info(s2.toString());
        log.info(s2.getFormateur().toString() + "FOM");
        List<Session> s1 = sessionService.findSessionByFormateur(f1.getId());
        assertNotNull(s1);


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
        Cours c = new Cours("first course", TypeCours.Informatique, "cours", setS, 10);
        coursService.addCours(c);
        Cours c1 = new Cours("first course", TypeCours.Informatique, "cours", setS, 20);
        coursService.addCours(c1);
        setC.add(c);
        setC.add(c1);
        Session s = new Session(date1, date2, 3L, "First session, month long",setC);
        sessionService.addSession(s);
        s.setSalaireF(500L);
        sessionService.modifierSession(s);
        setS.add(s);
        sessionService.budgerSession(s.getId(), s.getSalaireF());
        if(s.getPrice()!=null){
            assertEquals(s.getPrice(),Double.valueOf (590));
            sessionService.supprimerSession(s.getId());
            coursService.supprimerCours(c.getId());
        }
    }
}
