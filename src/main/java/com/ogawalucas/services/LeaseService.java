package com.ogawalucas.services;

import com.ogawalucas.entities.Lease;
import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;

import java.time.LocalDate;
import java.util.Objects;

public class LeaseService {

    public Lease lease(User user, Movie movie) {
        return new Lease(user, movie, LocalDate.now(), LocalDate.now().plusDays(1), movie.leasePrice());
    }

    public static void main(String[] args) {
        // Scenery
        var user = new User("name");
        var movie = new Movie("movie", 1, 1.0);

        // Action
        var lease = new LeaseService().lease(user, movie);

        // Verification
        System.out.println(lease.value() == 1.0);
        System.out.println(Objects.equals(lease.leaseDate(), LocalDate.now()));
        System.out.println(Objects.equals(lease.returnDate(), LocalDate.now().plusDays(1)));
    }
}