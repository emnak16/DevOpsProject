package com.esprit.examen;

import com.esprit.examen.entities.*;
import com.esprit.examen.exception.BadDataException;
import com.esprit.examen.exception.LogInException;
import com.esprit.examen.services.ICoursService;
import com.esprit.examen.services.IFormateurService;
import com.esprit.examen.services.ISessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)

@Rollback
@SpringBootTest
// we must delete the entity that we added right after the test is verified
// rolling back transactions made on the database during the test
public class FormateurServiceTest {

    @Autowired
    IFormateurService formateurService;

    @Autowired
    ISessionService sessionService;

    @Autowired
    ICoursService coursService;

    @Test
    public void addorEditFormateurTest() throws BadDataException {
        Formateur f = new Formateur("khouloud", "Ben Taoues", Poste.INGENIEUR, Contrat.CDI, "97189195", "kbentaoues@vermeg.com", "Khouloud@123");
        formateurService.addorEditFormateur(f);
        Formateur f2;
        f2 = formateurService.listFormateurs().stream().filter(formateur -> formateur.toString().equals(f.toString())).findFirst().get();

        assertNotNull(f2);
        f.setEmail("walid.besbes@gmail.com");
        formateurService.addorEditFormateur(f);
        f2 = formateurService.listFormateurs().stream().filter(formateur -> formateur.toString().equals(f.toString())).findFirst().get();
        assertNotNull(f2);
        formateurService.supprimerFormateur(f2.getId());
    }


    @Test
    public void supprimerFormateurTest() throws BadDataException {
        Formateur f = new Formateur("walid", "besbes", Poste.INGENIEUR, Contrat.CDI, "95131212", "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        formateurService.supprimerFormateur(f.getId());
        boolean res = formateurService.listFormateurs().stream().anyMatch(formateur -> formateur.toString().equals(f.toString()));
        assertFalse(res);
    }

    @Test
    public void nombreFormateursImpliquesDansUnCoursTest() throws Exception {
        Date date1 = null;
        Date date2 = null;
        date2 = new java.sql.Date(new Date().getTime());
        date1 = new java.sql.Date(new Date().getTime());
        Formateur f2;
        Formateur f = new Formateur("walid", "besbes", Poste.INGENIEUR, Contrat.CDI, "95131212", "wbesbes@vermeg.com", "Khouloud@123");
        formateurService.addorEditFormateur(f);
        f2 = formateurService.listFormateurs().stream().filter(formateur -> formateur.toString().equals(f.toString())).findFirst().get();
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);
        Session s2 = sessionService.listSession().stream().filter(session -> session.toString().equals(s.toString())).findFirst().get();
        sessionService.affecterFormateurASession(f2.getId(), s2.getId());
        Cours c = new Cours("first course", TypeCours.INFORMATIQUE, "cours", 10);
        coursService.addCours(c);
        Cours c2 = coursService.getCours().stream().filter(cours -> cours.toString().equals(c.toString())).findFirst().get();
        coursService.affecterCoursASession(c2.getId(), s2.getId());
        long nbFormateur = formateurService.nombreFormateursImpliquesDansUnCours(TypeCours.INFORMATIQUE);
        assertEquals(1L, nbFormateur);
        sessionService.supprimerSession(s2.getId());
        formateurService.supprimerFormateur(f2.getId());
        coursService.supprimerCours(c2.getId());
    }

    @Test
    public void listFormateurs() throws BadDataException {
        Formateur f = new Formateur("walid", "besbes", Poste.INGENIEUR, Contrat.CDI, "95131212", "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        List <Formateur> formateurList= formateurService.listFormateurs();
        assertNotEquals(formateurList.size(), 0);
        formateurService.supprimerFormateur(f.getId());

    }

    @Test
    public void login() throws BadDataException, LogInException {
        Formateur f = new Formateur("walid", "besbes", Poste.INGENIEUR, Contrat.CDI, "95131212", "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);

        int res = formateurService.logIn(f.getEmail(), "Vermeg+123");
        assertEquals(1, res);
        res = formateurService.logIn(f.getEmail(), "kh");
        assertNotEquals(1, res);
        formateurService.supprimerFormateur(f.getId());


    }

}
