package com.ogawalucas.services;

import com.ogawalucas.exceptions.InvalidNumberException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorServiceTest {

    private CalculatorService service;

    @Before
    public void setUp() {
        service = new CalculatorService();
    }

    @Test
    public void shouldSumTwoValues() {
        // Scenery
        var a = 1;
        var b = 2;

        // Action
        var result = service.sum(a, b);

        // Verification
        Assert.assertEquals(3, result);
    }

    @Test
    public void shouldSubtractTwoValues() {
        // Scenery
        var a = 1;
        var b = 2;

        // Action
        var result = service.subtract(a, b);

        // Verification
        Assert.assertEquals(-1, result);
    }

    @Test
    public void shouldDivideTwoValues() throws InvalidNumberException {
        // Scenery
        var a = 1;
        var b = 2;

        // Action
        var result = service.divide(a, b);

        // Verification
        Assert.assertEquals(0, result);
    }

    @Test(expected = InvalidNumberException.class)
    public void shouldThrowExceptionWhenDividePerZero() throws InvalidNumberException {
        // Scenery
        var a = 1;
        var b = 0;

        // Action
        var result = service.divide(a, b);

        // Verification
        Assert.assertEquals(0, result);
    }
}
