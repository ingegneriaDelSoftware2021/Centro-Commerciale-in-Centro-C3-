package it.unicam.cs.ids.c3;

import it.unicam.cs.ids.c3.model.database.DBLocale;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {


    @Test
    public void testCorriereLogin() throws ClassNotFoundException {
        assertEquals("CORRIERE",DBLocale.getInstance().checklogin("pluto","pippo","CORRIERE"));
        assertEquals("",DBLocale.getInstance().checklogin("asdsadf","pippo","CORRIERE"));
    }

    @Test
    public void testClienteLogin() throws ClassNotFoundException {
        assertEquals("CLIENTE",DBLocale.getInstance().checklogin("pluto","pippo","CLIENTE"));
        assertEquals("",DBLocale.getInstance().checklogin("asdsadf","pippo","CLIENTE"));
    }

    @Test
    public void testLockerLogin() throws ClassNotFoundException {
        assertEquals("LOCKER",DBLocale.getInstance().checklogin("pluto","pippo","LOCKER"));
        assertEquals("",DBLocale.getInstance().checklogin("asdsadf","pippo","LOCKER"));
    }

    @Test
    public void testCommercianteLogin() throws ClassNotFoundException {
        assertEquals("COMMERCIANTE",DBLocale.getInstance().checklogin("a","d","COMMERCIANTE"));
        assertEquals("",DBLocale.getInstance().checklogin("asdsadf","pippo","COMMERCIANTE"));
    }
}
