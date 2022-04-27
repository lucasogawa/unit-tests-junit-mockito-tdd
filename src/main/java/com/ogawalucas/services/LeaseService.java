package com.ogawalucas.services;

import com.ogawalucas.entities.Lease;
import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import com.ogawalucas.exceptions.LeaseException;
import com.ogawalucas.exceptions.MovieWithoutStockException;

import java.time.LocalDate;
import java.util.List;

public class LeaseService {

    public Lease lease(User user, List<Movie> movies) throws LeaseException, MovieWithoutStockException {
        if (user == null) {
            throw new LeaseException("User empty.");
        }
        if (movies == null || movies.isEmpty()) {
            throw new LeaseException("Movie empty.");
        }
        if (movies.stream().anyMatch(movie -> movie.stock().equals(0))) {
            throw new MovieWithoutStockException();
        }

        return new Lease(
            user,
            movies,
            LocalDate.now(),
            LocalDate.now().plusDays(1),
            movies.stream()
                .map(Movie::leasePrice)
                .reduce(0.0, Double::sum)
        );
    }
}