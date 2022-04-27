package com.ogawalucas.services;

import com.ogawalucas.exceptions.InvalidNumberException;

public class CalculatorService {

    public int sum(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int divide(int a, int b) throws InvalidNumberException {
        if (b == 0) {
            throw new InvalidNumberException("Can't divide by zero");
        }

        return a/b;
    }
}
