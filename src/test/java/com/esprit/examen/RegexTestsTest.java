package com.esprit.examen;


import com.esprit.examen.config.RegexTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)

@SpringBootTest
public class RegexTestsTest {


    @Test
    public void isValidPasswordTest() {
        assertTrue(RegexTests.isValidPassword("Khouloud@123"));//compliant
        assertFalse(RegexTests.isValidPassword("12345678"));//only numbers
        assertFalse(RegexTests.isValidPassword("khouloud@123"));//only lowerCase letters
        assertFalse(RegexTests.isValidPassword("KHOULOUD@123"));//only upperCase letters
        assertFalse(RegexTests.isValidPassword("Khouloud123"));//no special caracters
        assertFalse(RegexTests.isValidPassword("kho"));// length <8
        assertFalse(RegexTests.isValidPassword(""));

    }

    @Test
    public void isValidPhoneTest() {
        assertTrue(RegexTests.isAvalidPhone("97189195")); //compliant
        assertFalse(RegexTests.isAvalidPhone("a1235678")); //contain letter
        assertFalse(RegexTests.isAvalidPhone("kh@12364"));
        assertFalse(RegexTests.isAvalidPhone(""));


    }

    @Test
    public void isValidMailTest() {
        assertTrue(RegexTests.isValidMail("khouloud.bentaoues33@gmail.com")); //compliant
        assertFalse(RegexTests.isValidMail("hk.at"));
        assertFalse(RegexTests.isValidMail("123@"));
        assertFalse(RegexTests.isValidMail(""));



    }

    @Test
    public void isValidNameTest() {
        assertTrue(RegexTests.isValidName("khouloud ben taoues"));
        assertFalse(RegexTests.isValidName("123")); //contains numbers
        assertFalse(RegexTests.isValidName("khouloud.kh")); //contains special characters
        assertFalse(RegexTests.isValidName(""));

    }
}
