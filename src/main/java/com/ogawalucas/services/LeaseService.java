package com.ogawalucas.services;

import com.ogawalucas.entities.Lease;
import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;

import java.time.LocalDate;

public class LeaseService {

    public Lease lease(User user, Movie movie) throws Exception {
        if (movie.stock().equals(0)) {
            throw new Exception("Movie without stock.");
        }

        return new Lease(user, movie, LocalDate.now(), LocalDate.now().plusDays(1), movie.leasePrice());
    }
}