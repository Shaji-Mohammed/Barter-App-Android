package com.example.barterapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void productTest() {
        Product product = new Product();

        assertEquals(product.name, null);
        assertEquals(product.value, 0);
        assertEquals(product.seller, null);

    }
}