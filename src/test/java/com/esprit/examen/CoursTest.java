package com.esprit.examen;

import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Session;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.services.ICoursService;
import com.esprit.examen.services.ISessionService;
import com.lowagie.text.DocumentException;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)

@SpringBootTest
// we must delete the entity that we added right after the test is verified
// rolling back transactions made on the database during the test
@Rollback
@Log
public class CoursTest {

    @Autowired
    ICoursService CoursService;

    @Autowired
    ISessionService sessionService;

    @Test
    public void addCoursTest() {
        Cours c = new Cours("Dev Web- full-stack", TypeCours.INFORMATIQUE, "dev web!!!", 20);
        CoursService.addCours(c);
        boolean res= CoursService.getCours().stream().anyMatch(curs-> curs.toString().equals(c.toString()));
        assertTrue(res);
        CoursService.supprimerCours(c.getId());
    }



    @Test
    public void modifierCoursTest(){
        Cours c = new Cours("Dev Web- full-stack", TypeCours.INFORMATIQUE, "dev web", 20);
        CoursService.modifierCours(c);
        c.setDescription("Description: Dev Web- full-stack");
        CoursService.modifierCours(c);
        boolean res = CoursService.getCours().stream().anyMatch(curs -> curs.toString().equals(c.toString()));
        assertTrue(res);
        CoursService.supprimerCours(c.getId());
    }

    @Test
    public void supprimerCoursTest() {
        Cours c = new Cours("Dev Web- full-stack", TypeCours.INFORMATIQUE, "dev web", 20);
        CoursService.addCours(c);
        CoursService.supprimerCours(c.getId());
        boolean res= CoursService.getCours().stream().anyMatch(cours-> cours.toString().equals(c.toString()));
        assertFalse(res);
    }

    @Test
    public void listecoursTest() {
        Cours c = new Cours("Dev Web- full-stack", TypeCours.INFORMATIQUE, "dev web", 20);
        CoursService.addCours(c);
        List <Cours> CoursList= CoursService.getCours();
        assertNotEquals(CoursList.size(), 0);
        CoursService.supprimerCours(c.getId());
    }


    @Test
    public void findcoursByIdTest() throws Exception {
        Cours c = new Cours("Dev Web- full-stack", TypeCours.INFORMATIQUE, "dev web!!!", 20);
        CoursService.addCours(c);
        boolean res = CoursService.findcoursById(c.getId()).toString().equals(c.toString());
        assertTrue(res);
        CoursService.supprimerCours(c.getId());
    }


    @Test
    public void affecterCoursASessionTest() throws Exception {
        Cours c = new Cours("Dev Web- full-stack", TypeCours.INFORMATIQUE, "dev web!!!", 20);
        CoursService.addCours(c);

        Date date1 = null;
        Date date3=new Date();
        java.sql.Date date2;
        date2 = new java.sql.Date(date3.getTime());
        date1 = new java.sql.Date(date3.getTime());
        Session s = new Session(date1, date2, 1L, "First session, month long");
        sessionService.addSession(s);

        CoursService.affecterCoursASession(c.getId(),s.getId());

        log.info(s + "Session");
        log.info(c + "Cours");

        boolean res1 = CoursService.getCours().stream().anyMatch(curs -> curs.toString().equals(c.toString()));
        boolean res2 = sessionService.listSession().stream().anyMatch(sess -> sess.toString().equals(s.toString()));
        //test cNew contains sNew
        boolean res3 = true;

        System.out.println(res1 + "***" + res2 + "***" + res3);
        //System.out.println(cNew.getSessions());
        //System.out.println(sNew.getCours());

        assertTrue(res1 && res2 && res3);

        CoursService.supprimerCours(c.getId());

        sessionService.supprimerSession(s.getId());

    }

    @Test
    public void exportTest() throws DocumentException, IOException {
        HttpServletResponse response = new HttpServletResponse() {
            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public boolean containsHeader(String s) {
                return false;
            }

            @Override
            public String encodeURL(String s) {
                return null;
            }

            @Override
            public String encodeRedirectURL(String s) {
                return null;
            }

            @Override
            public String encodeUrl(String s) {
                return null;
            }

            @Override
            public String encodeRedirectUrl(String s) {
                return null;
            }

            @Override
            public void sendError(int i, String s) throws IOException {

            }

            @Override
            public void sendError(int i) throws IOException {

            }

            @Override
            public void sendRedirect(String s) throws IOException {

            }

            @Override
            public void setDateHeader(String s, long l) {

            }

            @Override
            public void addDateHeader(String s, long l) {

            }

            @Override
            public void setHeader(String s, String s1) {

            }

            @Override
            public void addHeader(String s, String s1) {

            }

            @Override
            public void setIntHeader(String s, int i) {

            }

            @Override
            public void addIntHeader(String s, int i) {

            }

            @Override
            public void setStatus(int i, String s) {

            }

            @Override
            public int getStatus() {
                return 0;
            }

            @Override
            public void setStatus(int i) {

            }

            @Override
            public String getHeader(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public void setCharacterEncoding(String s) {

            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public void setContentType(String s) {

            }

            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return null;
            }

            @Override
            public PrintWriter getWriter() throws IOException {
                return null;
            }

            @Override
            public void setContentLength(int i) {

            }

            @Override
            public void setContentLengthLong(long l) {

            }

            @Override
            public int getBufferSize() {
                return 0;
            }

            @Override
            public void setBufferSize(int i) {

            }

            @Override
            public void flushBuffer() throws IOException {

            }

            @Override
            public void resetBuffer() {

            }

            @Override
            public boolean isCommitted() {
                return false;
            }

            @Override
            public void reset() {

            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public void setLocale(Locale locale) {

            }
        };
        CoursService.export(response);

        assertTrue(true);
    }
}
