package com.esprit.examen;

import java.util.List;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.services.ICoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)

@SpringBootTest
public class CoursTest {

    @Autowired
    ICoursService CoursService;


    @Test
    public void addCoursTest() {
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web", 20);
        CoursService.addCours(c);
        boolean res= CoursService.listCours().stream().anyMatch(cours-> cours.toString().equals(c.toString()));
        System.out.println(res);
        assertTrue(res);
        CoursService.supprimerCours(c.getId());
    }



    @Test
    public void modifierCoursTest(){
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web", 20);
        CoursService.addCours(c);
        c.setDescription("Description: Dev Web- full-stack");
        CoursService.modifierCours(c);
        CoursService.supprimerCours(c.getId());

    }

    @Test
    public void supprimerCours() {
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web", 20);
        CoursService.addCours(c);
        CoursService.supprimerCours(c.getId());
        boolean res= CoursService.listCours().stream().anyMatch(cours-> cours.toString().equals(c.toString()));
        assertFalse(res);
    }

    @Test
    public void listcours() {
        Cours c = new Cours("Dev Web- full-stack",TypeCours.Informatique,"dev web", 20);
        CoursService.addCours(c);
        List <Cours> CoursList= CoursService.listCours();
        assertNotEquals(CoursList.size(), 0);
        CoursService.supprimerCours(c.getId());

    }
}
