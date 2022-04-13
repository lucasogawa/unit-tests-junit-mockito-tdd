package com.ogawalucas.services;

import com.ogawalucas.entities.Lease;
import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;

import java.time.LocalDate;

public class LeaseService {

    public Lease lease(User user, Movie movie) {
        return new Lease(user, movie, LocalDate.now(), LocalDate.now().plusDays(1), movie.leasePrice());
    }
}