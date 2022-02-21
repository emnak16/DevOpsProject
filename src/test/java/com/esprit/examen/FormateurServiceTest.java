package com.esprit.examen;

import java.text.Normalizer;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.esprit.examen.entities.*;
import com.esprit.examen.services.ICoursService;
import com.esprit.examen.services.IFormateurService;
import com.esprit.examen.services.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)

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
    public void addFormateurTest() {
        Formateur f = new Formateur("khouloud", "Ben Taoues", Poste.Ingénieur, Contrat.CDI, "97189195", "kbentaoues@vermeg.com", "kbentaoues+123");
        formateurService.addorEditFormateur(f);
        boolean res = formateurService.listFormateurs().stream().anyMatch(formateur -> formateur.toString().equals(f.toString()));
        System.out.println(res);
        assertTrue(res);
    }


    @Test
    public void modifierFormateurTest() {
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI,"95131212", "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        f.setEmail("walid.besbes@gmail.com");
        formateurService.addorEditFormateur(f);
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
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Set<Session> setS = new HashSet<Session>();
        Set<Cours> setC = new HashSet<Cours>();
        Cours c = new Cours("first course", TypeCours.Informatique, "cours", setS, 10L);
        coursService.addCours(c);
        setC.add(c);
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI, "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        Session s = new Session(date1, date2, 1L, "First session, month long", setC, f);
        sessionService.addSession(s);
        setS.add(s);

        Long nbFormateur= formateurService.nombreFormateursImpliquesDansUnCours(TypeCours.Informatique);
        assertEquals(java.util.Optional.ofNullable(nbFormateur),1);




    }

    @Test
    public void listFormateurs() {
        Formateur f = new Formateur("walid", "besbes", Poste.Ingénieur, Contrat.CDI,"95131212", "wbesbes@vermeg.com", "Vermeg+123");
        formateurService.addorEditFormateur(f);
        List <Formateur> formateurList= formateurService.listFormateurs();
        assertNotEquals(formateurList.size(), 0);

    }
}
