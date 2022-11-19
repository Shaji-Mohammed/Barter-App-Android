package com.example.barterapp;

import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormValidatorTest {
    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void checkIfStringIsEmpty() {
        assertTrue(FormValidator.isEmpty(""));
        assertTrue(FormValidator.isEmpty(null));
    }

    @Test
    public void checkIfStringIsNotEmpty() {
        assertFalse(FormValidator.isEmpty("Hello"));
        assertFalse(FormValidator.isEmpty("Super Long String That Is Really Long"));
    }

    @Test
    public void checkIfPasswordIsValid() {
        assertTrue(FormValidator.isValidPassword("Password123"));
        assertTrue(FormValidator.isValidPassword("!@#$%^&*() /.,"));
    }

    @Test
    public void checkIfPasswordIsInvalid() {
        assertFalse(FormValidator.isValidPassword(""));
    }

    @Test
    public void checkIfEmailIsValid() {
        assertTrue(FormValidator.isValidEmail("test@email.ca"));
        assertTrue(FormValidator.isValidEmail("a@dal.ca"));
    }

    @Test
    public void checkIfEmailIsInvalid() {
        assertFalse(FormValidator.isValidEmail("wadw#dsa.ca"));
        assertFalse(FormValidator.isValidEmail(null));
        assertFalse(FormValidator.isValidEmail(""));
        assertFalse(FormValidator.isEmpty("dw@dwca"));
    }
}
