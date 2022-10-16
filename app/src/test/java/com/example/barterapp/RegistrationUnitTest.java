package com.example.barterapp;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationUnitTest {
    static MainActivity mainActivity;

    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkIfStringIsEmpty() {
        assertTrue(mainActivity.isEmpty(""));
        assertTrue(mainActivity.isEmpty(null));
    }

    @Test
    public void checkIfStringIsNotEmpty() {
        assertFalse(mainActivity.isEmpty("Hello"));
        assertFalse(mainActivity.isEmpty("Super Long String That Is Really Long"));
    }

    @Test
    public void checkIfPasswordIsValid() {
        assertTrue(mainActivity.isPassword("Password123"));
        assertTrue(mainActivity.isPassword("!@#$%^&*() /.,"));
    }

    @Test
    public void checkIfPasswordIsInvalid() {
        assertFalse(mainActivity.isPassword(""));
    }

    @Test
    public void checkIfEmailIsValid() {
        assertTrue(mainActivity.isEmail("test@email.ca"));
        assertTrue(mainActivity.isEmail("a@dal.ca"));
    }

    @Test
    public void checkIfEmailIsInvalid() {
        assertFalse(mainActivity.isEmail("wadw#dsa.ca"));
        assertFalse(mainActivity.isEmail(null));
        assertFalse(mainActivity.isEmail(""));
        assertFalse(mainActivity.isEmpty("dw@dwca"));
    }
}
