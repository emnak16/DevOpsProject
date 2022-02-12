package com.esprit.examen;

import com.esprit.examen.entities.Session;
import com.esprit.examen.services.ISessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionTest {

    @Autowired
    ISessionService sessionService;

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


}
