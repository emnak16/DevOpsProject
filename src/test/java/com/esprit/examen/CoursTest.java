package com.esprit.examen;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Session;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.services.ICoursService;
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

@SpringBootTest
// we must delete the entity that we added right after the test is verified
// rolling back transactions made on the database during the test
@Rollback
public class CoursTest {

    @Autowired
    ICoursService CoursService;

    @Autowired
    ISessionService sessionService;

    @Test
    public void addCoursTest() {
        Cours c = new Cours("Dev Web- full-stack", TypeCours.Informatique, "dev web!!!", 20.0);
        CoursService.addCours(c);
        boolean res= CoursService.getCours().stream().anyMatch(curs-> curs.toString().equals(c.toString()));
        assertTrue(res);
//        CoursService.supprimerCours(c.getId());
    }



    @Test
    public void modifierCoursTest(){
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web", 20);
        CoursService.modifierCours(c);
        c.setDescription("Description: Dev Web- full-stack");
        CoursService.modifierCours(c);
        CoursService.supprimerCours(c.getId());

    }

    @Test
    public void supprimerCoursTest() {
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web", 20);
        CoursService.addCours(c);
        CoursService.supprimerCours(c.getId());
        boolean res= CoursService.getCours().stream().anyMatch(cours-> cours.toString().equals(c.toString()));
        assertFalse(res);
    }

    @Test
    public void listecoursTest() {
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web", 20);
        CoursService.addCours(c);
        List <Cours> CoursList= CoursService.getCours();
        assertNotEquals(CoursList.size(), 0);
        CoursService.supprimerCours(c.getId());

    }


    @Test
    public void findcoursByIdTest() {
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web!!!", 20);
        CoursService.addCours(c);
        boolean res= false;
        try {
            res = CoursService.findcoursById(c.getId()).toString().equals(c.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(res);
        CoursService.supprimerCours(c.getId());

    }


    @Test
    public void affecterCoursASessionTest() {
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web!!!", 20);
        CoursService.addCours(c);

        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);


        CoursService.affecterCoursASession(c.getId(),s.getId());
        //à completer
    }
}
