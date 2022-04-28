package com.ogawalucas.services;

import com.ogawalucas.entities.Lease;
import com.ogawalucas.entities.Movie;
import com.ogawalucas.entities.User;
import com.ogawalucas.exceptions.LeaseException;
import com.ogawalucas.exceptions.MovieWithoutStockException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

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
            calculateReturnDate(LocalDate.now()),
            calculateValue(movies)
        );
    }

    private Double calculateValue(List<Movie> movies) {
        return IntStream.rangeClosed(0, movies.size() - 1)
            .mapToObj(index -> switch (index) {
                case 2 -> 0.75 * movies.get(index).leasePrice();
                case 3 -> 0.50 * movies.get(index).leasePrice();
                case 4 -> 0.25 * movies.get(index).leasePrice();
                case 5 -> 0.00 * movies.get(index).leasePrice();
                default -> movies.get(index).leasePrice();
            })
            .reduce(0.0, Double::sum);
    }

    private LocalDate calculateReturnDate(LocalDate leaseDate) {
        var returnDate = leaseDate.plusDays(1);

        if (leaseDate.plusDays(1).getDayOfWeek() == DayOfWeek.SUNDAY) {
            return returnDate.plusDays(1);
        }

        return returnDate;
    }
}