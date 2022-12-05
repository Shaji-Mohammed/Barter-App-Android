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

    @Test
    public void checkIfItemNameIsValid() {
        assertTrue(FormValidator.isValidItemName("Cat"));
        assertTrue(FormValidator.isValidItemDesc("Shirt"));
    }

    @Test
    public void checkIfItemNameIsInvalid() {
        assertFalse(FormValidator.isValidItemName("bo"));
        assertFalse(FormValidator.isValidItemName("some super long name that shouldn't be valid"));
        assertFalse(FormValidator.isValidItemName(null));
    }

    @Test
    public void checkIfItemDescIsValid() {
        assertTrue(FormValidator.isValidItemDesc("This is a small cat"));
        assertTrue(FormValidator.isValidItemDesc("A bright blue shirt"));
    }

    @Test
    public void checkIfItemDescIsInvalid() {
        assertFalse(FormValidator.isValidItemDesc("smol"));
        assertFalse(FormValidator.isValidItemDesc("a description can be super long so this will take a while to type now maybe it is too long."));
        assertFalse(FormValidator.isValidItemDesc(null));
    }
}
