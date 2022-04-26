package com.ogawalucas.services;

import com.ogawalucas.entities.Lease;
import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import com.ogawalucas.exceptions.LeaseException;
import com.ogawalucas.exceptions.MovieWithoutStockException;

import java.time.LocalDate;

public class LeaseService {

    public Lease lease(User user, Movie movie) throws LeaseException, MovieWithoutStockException {
        if (user == null) {
            throw new LeaseException("User empty.");
        }
        if (movie == null) {
            throw new LeaseException("Movie empty.");
        }
        if (movie.stock().equals(0)) {
            throw new MovieWithoutStockException();
        }

        return new Lease(user, movie, LocalDate.now(), LocalDate.now().plusDays(1), movie.leasePrice());
    }
}