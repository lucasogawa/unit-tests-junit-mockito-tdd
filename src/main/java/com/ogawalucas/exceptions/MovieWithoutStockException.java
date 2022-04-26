package com.ogawalucas.exceptions;

public class MovieWithoutStockException extends Exception {
    public MovieWithoutStockException() {
        super("Movie without stock.");
    }
}
