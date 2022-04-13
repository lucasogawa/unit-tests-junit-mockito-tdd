package com.ogawalucas.services;

import com.ogawalucas.entities.Lease;
import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;

import java.time.LocalDateTime;

public class LeaseService {

    public Lease lease(User user, Movie movie) {
        var lease = new Lease(user, movie, LocalDateTime.now(), LocalDateTime.now().plusDays(1), movie.leasePrice());

        //TODO Add method to lease

        return lease;
    }
}