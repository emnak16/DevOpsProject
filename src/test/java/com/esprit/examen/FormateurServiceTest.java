package com.esprit.examen;

import com.esprit.examen.entities.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)

@SpringBootTest
// we must delete the entity that we added right after the test is verified
// rolling back transactions made on the database during the test
@Rollback
public class FormateurServiceTest {

    @Autowired
    IFormateurService formateurService;

    @Autowired
    ISessionService sessionService;

    @Autowired
    ICoursService coursService;

    @Test
    public void addorEditFormateurTest() {
        Formateur f = new Formateur("khouloud", "Ben Taoues", Poste.Ingénieur, Contrat.CDI, "97189195", "kbentaoues@vermeg.com", "Khouloud@123");
        formateurService.addorEditFormateur(f);
        boolean res = formateurService.listFormateurs().stream().anyMatch(formateur -> formateur.toString().equals(f.toString()));
        assertTrue(res);
        f.setEmail("walid.besbes@gmail.com");
        formateurService.addorEditFormateur(f);
        res = formateurService.listFormateurs().stream().anyMatch(formateur -> formateur.toString().equals(f.toString()));
        assertTrue(res);
    }


    @Test
    public void supprimerFormateurTest() {
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI,"95131212", "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        formateurService.supprimerFormateur(f.getId());
        boolean res = formateurService.listFormateurs().stream().anyMatch(formateur -> formateur.toString().equals(f.toString()));
        assertFalse(res);
    }

    @Test
    public void nombreFormateursImpliquesDansUnCoursTest() {
        Date date1 = null;
        Date date2 = null;
        date2 = new java.sql.Date(new Date().getTime());
        date1 = new java.sql.Date(new Date().getTime());
        Set<Session> setS = new HashSet<Session>();
        Set<Cours> setC = new HashSet<Cours>();
        Cours c = new Cours("first course", TypeCours.Informatique, "cours", setS, 10);
        coursService.addCours(c);
        setC.add(c);
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI, "95131212", "wbesbes@vermeg.com", "Khouloud@123");
        formateurService.addorEditFormateur(f);
        Session s = new Session(date1, date2, 1L, "First session, month long", setC, f);
        sessionService.addSession(s);
        setS.add(s);
        long nbFormateur = formateurService.nombreFormateursImpliquesDansUnCours(TypeCours.Informatique);
        assertEquals(1L, nbFormateur);
    }

    @Test
    public void listFormateurs() {
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI,"95131212", "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        List <Formateur> formateurList= formateurService.listFormateurs();
        assertNotEquals(formateurList.size(), 0);

    }


}
